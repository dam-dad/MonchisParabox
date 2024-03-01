package dad.monchisparabox.game.block;

import dad.auraengine.entities.movements.Direction;
import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.GameMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MapBlock extends Block {

    private ImageView mapView;
    private GameMap gameMap;

    //TODO Guardar de donde se entro por ultima vez (location)

    public MapBlock(Location location) {
        super(location);
        image = new Image("/assets/block/libre3.png");
        mapView = new ImageView(image);
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void updateBlock() {
        GridPane.setColumnIndex(mapView, location.getX());
        GridPane.setRowIndex(mapView, location.getY());
    }

    @Override
    public void render() {
        location.getMap().addEntity(mapView, location.getX(), location.getY());
    }

    @Override
    public void destroy() {
        location.getMap().getBlocks().remove(this);
        location.getMap().getChildren().remove(mapView);
    }
}
