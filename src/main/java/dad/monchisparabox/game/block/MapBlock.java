package dad.monchisparabox.game.block;

import dad.auraengine.entities.movements.Direction;
import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.App;
import dad.monchisparabox.game.GameMap;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;

public class MapBlock extends Block {

    private ImageView mapView;
    private GameMap gameMap;

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
        if(rendered) return;
        location.getMap().addEntity(mapView, location.getX(), location.getY());
        rendered = true;
    }

    @Override
    public void destroy() {
        location.getMap().getBlocks().remove(this);
        location.getMap().getChildren().remove(mapView);
        rendered = false;
    }
}
