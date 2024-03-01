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

    public Direction getFacing() {
        if(getGameMap().getStart().getX() == 0 && getGameMap().getStart().getX() < getGameMap().getMapMaxWidth()-1 && getGameMap().getStart().getY() > 0 && getGameMap().getStart().getY() < getGameMap().getMapMaxHeight()-1) {
            return Direction.LEFT;
        } else if(getGameMap().getStart().getX() == getGameMap().getMapMaxWidth()-1 && getGameMap().getStart().getY() > 0 && getGameMap().getStart().getY() < getGameMap().getMapMaxHeight()-1) {
            return Direction.RIGHT;
        } else if(getGameMap().getStart().getX() < getGameMap().getMapMaxWidth()-1 && getGameMap().getStart().getX() > 0 && getGameMap().getStart().getY() == 0) {
            return Direction.UP;
        } else if(getGameMap().getStart().getX() < getGameMap().getMapMaxWidth()-1 && getGameMap().getStart().getX() > 0 && getGameMap().getStart().getY() == getGameMap().getMapMaxHeight()-1) {
            return Direction.DOWN;
        }
        return null;
    }

    public void updateBlock() {
        GridPane.setColumnIndex(mapView, location.getX());
        GridPane.setRowIndex(mapView, location.getY());
    }

    @Override
    public void render() {
        location.getMap().addEntity(mapView, location.getX(), location.getY());
        getFacing();
    }

    @Override
    public void destroy() {
        location.getMap().getBlocks().remove(this);
        location.getMap().getChildren().remove(mapView);
    }
}
