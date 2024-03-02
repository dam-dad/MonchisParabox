package dad.monchisparabox.game;

import java.util.ArrayList;


import dad.auraengine.entities.Entity;
import dad.auraengine.entities.StaticEntity;
import dad.auraengine.entities.movements.Direction;
import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.App;
import dad.monchisparabox.game.block.LimitBlock;
import dad.monchisparabox.game.block.MapBlock;
import dad.monchisparabox.game.entities.Player;
import dad.monchisparabox.game.block.Block;
import dad.monchisparabox.game.block.BoxBlock;
import dad.monchisparabox.game.utilities.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

public class Game extends AnimationTimer {

    private Player player;
    private ArrayList<GameMap> maps = new ArrayList<>();
    private BorderPane root;

    public Game(BorderPane root, String tiledMap) {
        this.root = root;
        maps.addAll(Tile.tiles(tiledMap));

        player = new Player();
        player.setLocation(maps.get(0).getStart());

        root.setOnKeyPressed(event -> handleKeyPress(event.getCode()));

        Platform.runLater(root::requestFocus);

        init();
    }

    public Player getPlayer() {
        return player;
    }

    public GameMap getInitialMap() {
        return maps.get(0);
    }

    public void init() {
        getInitialMap().load();
        player.render();
    }

    public void changeMap(GameMap gameMap, Location location, boolean joining) {
        Block door = gameMap.getBlockAt(location, null);
        if (door == null) {
            if(joining)
                gameMap.setJoinLocation(new Location(player.getLocation().getMap(), player.getLocation().getLastX(), player.getLocation().getLastY()));
            player.setLocation(location.clone());
            player.getLocation().setLastX(location.getX());
            player.getLocation().setLastY(location.getY());
            player.render();
            gameMap.load();
            root.setCenter(gameMap);
        }
    }

    public boolean teleportBlockToMap(GameMap gameMapTo, Block block, Location location) {
        Block door = gameMapTo.getBlockAt(location, null);
        if (door == null) {
            System.out.println("Teleporting block to map | " + block.getClass().getName());
            block.destroy();

            block.setLocation(location.clone());
            if (gameMapTo.getFacing() == Direction.RIGHT) {
                block.push(Direction.LEFT);
            } else if (gameMapTo.getFacing() == Direction.LEFT) {
                block.push(Direction.RIGHT);
            } else if (gameMapTo.getFacing() == Direction.UP) {
                block.push(Direction.DOWN);
            } else if (gameMapTo.getFacing() == Direction.DOWN) {
                block.push(Direction.UP);
            }

            location.getMap().getBlocks().add(block);
            return true;
        }
        return false;
    }

    // game loop
    @Override
    public void handle(long now) {
        player.getLocation().getMap().handleMap();
        player.updatePlayer();
    }

    private void handleKeyPress(KeyCode code) {
        if (code == KeyCode.W) {
            player.handleMovement(Direction.UP);
        } else if (code == KeyCode.A) {
            player.handleMovement(Direction.LEFT);
        } else if (code == KeyCode.S) {
            player.handleMovement(Direction.DOWN);
        } else if (code == KeyCode.D) {
            player.handleMovement(Direction.RIGHT);
        }

        if(code == KeyCode.R) {
            App.getMainController().setGame(new Game(root, Tile.mapa));
        }
    }
}
