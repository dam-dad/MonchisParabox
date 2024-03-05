package dad.monchisparabox.game.block;

import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.GameMap;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Clase que representa un bloque de mapa en el juego Monchi's Parabox.
 */
public class MapBlock extends Block {

    private ImageView mapView;
    private GameMap gameMap;

    /**
     * Constructor de la clase MapBlock.
     *
     * @param location La ubicación del bloque de mapa.
     */
    public MapBlock(Location location) {
        super(location);
        image = new Image("/assets/block/libre3.png");
        mapView = new ImageView(image);
        mapView.setFitHeight(50);
        mapView.setFitWidth(50);
    }

    /**
     * Obtiene el mapa asociado a este bloque de mapa.
     *
     * @return El mapa asociado.
     */
    public GameMap getGameMap() {
        return gameMap;
    }

    /**
     * Establece el mapa asociado a este bloque de mapa y carga el mapa.
     *
     * @param gameMap El mapa a establecer.
     */
    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
        gameMap.load();
    }

    /**
     * Actualiza la vista del bloque de mapa.
     */
    public void updateBlock() {
        renderMapview();
        GridPane.setColumnIndex(mapView, location.getX());
        GridPane.setRowIndex(mapView, location.getY());
    }

    /**
     * Renderiza la vista del mapa.
     */
    public void renderMapview() {
        mapView.setImage(getGameMap().snapshot(new SnapshotParameters(), null));
    }

    /**
     * Renderiza el bloque de mapa en el mapa.
     */
    @Override
    public void render() {
        if(rendered) return;
        location.getMap().addEntity(mapView, location.getX(), location.getY());
        rendered = true;
    }

    /**
     * Destruye el bloque de mapa y lo elimina del mapa.
     */
    @Override
    public void destroy() {
        location.getMap().getBlocks().remove(this);
        location.getMap().getChildren().remove(mapView);
        rendered = false;
    }
}
