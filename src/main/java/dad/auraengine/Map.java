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

/**
 * La clase Map representa un mapa en una cuadrícula donde se pueden agregar entidades.
 */
public class Map extends GridPane {
    
    private static final double CELL_SIZE = 49.6;
    
    private int maxHeight;
    private int maxWidth;

    /**
     * Crea un nuevo mapa con las dimensiones máximas especificadas y un factor de escala.
     * 
     * @param maxX El ancho máximo del mapa.
     * @param maxY La altura máxima del mapa.
     * @param scale El factor de escala del mapa.
     */
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
    
    /**
     * Agrega una entidad al mapa en la posición especificada.
     * 
     * @param node La entidad que se va a agregar al mapa.
     * @param posX La posición X en la que se va a agregar la entidad.
     * @param posY La posición Y en la que se va a agregar la entidad.
     */
    public void addEntity(Node node, int posX, int posY) {
        add(node, posX, posY);
        
        // Recargamos la posición por si hay que escalar...
        setHalignment(node, HPos.CENTER);
        setValignment(node, VPos.CENTER);
    }
    
    /**
     * Obtiene la altura máxima del mapa.
     * 
     * @return La altura máxima del mapa.
     */
    public int getMapMaxHeight() {
        return maxHeight;
    }
    
    /**
     * Obtiene el ancho máximo del mapa.
     * 
     * @return El ancho máximo del mapa.
     */
    public int getMapMaxWidth() {
        return maxWidth;
    }
}

