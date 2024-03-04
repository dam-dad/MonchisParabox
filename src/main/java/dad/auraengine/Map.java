package dad.auraengine;

import dad.monchisparabox.App;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Map extends GridPane {
	
	private static final double CELL_SIZE = 49.6;
	
	private int maxHeight;
	private int maxWidth;

	
	public Map(int maxX, int maxY, double scale) {
		super(0, 0);
		
		maxHeight = maxY;
		maxWidth = maxX;
		
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
	
	public int getMapMaxHeight() {
		return maxHeight;
	}
	
	public int getMapMaxWidth() {
		return maxWidth;
	}
}
