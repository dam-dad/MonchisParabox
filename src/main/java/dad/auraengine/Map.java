package dad.auraengine;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class Map extends GridPane {
	
	private static final double CELL_SIZE = 50;
	
	public Map(int maxX, int maxY, double scale) {
		super(0, 0);
		
		setAlignment(Pos.CENTER);
		setGridLinesVisible(true);
		
		for (int i = 0; i < maxY; i++) {
	        RowConstraints rConstraint = new RowConstraints();
	        rConstraint.setMaxHeight(CELL_SIZE);
	        rConstraint.setMinHeight(CELL_SIZE);
	        rConstraint.setPrefHeight(CELL_SIZE);
	        rConstraint.setVgrow(Priority.NEVER);
	        getRowConstraints().add(rConstraint);
	    }

	    for (int i = 0; i < maxX; i++) {
	        ColumnConstraints cConstraint = new ColumnConstraints();
	        cConstraint.setMaxWidth(CELL_SIZE);
	        cConstraint.setMinWidth(CELL_SIZE);
	        cConstraint.setPrefWidth(CELL_SIZE);
	        cConstraint.setHgrow(Priority.NEVER);
	        getColumnConstraints().add(cConstraint);
	    }
	    
	    setScaleX(scale);
		setScaleY(scale);
	}
	
	//Y en verdad ahora es Node, pero el motor debería gestionar las entidades y eso...
	public void addEntity(Node node, int posX, int posY) {
		add(node, posX, posY);
		
		// Recargamos la posición por si hay que escalar...
		setHalignment(node, HPos.CENTER);
		setValignment(node, VPos.CENTER);
	}
	
	public double getCenterX(int posX) {
	    double cellWidth = getWidth() / getColumnConstraints().size();
	    return (cellWidth * (posX + 0.5) + getLayoutX()) * 10;
	}

	public double getCenterY(int posY) {
	    double cellHeight = getHeight() / getRowConstraints().size();
	    return (cellHeight * (posY + 0.5) + getLayoutY()) * 10;
	}
}
