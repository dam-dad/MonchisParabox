package dad.monchisparabox.game;

import java.util.ArrayList;


import dad.auraengine.entities.StaticEntity;
import dad.monchisparabox.game.block.LimitBlock;
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

        root.setOnKeyPressed(event -> {
            System.out.println(event.getCode());
            handleKeyPress(event.getCode());
        });

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

    public void changeMap(GameMap gameMap) {
        gameMap.load();
        root.setCenter(gameMap);
    }

    // game loop
    @Override
    public void handle(long now) {
        player.getLocation().getMap().handleMap();
        player.updatePlayer();
    }

    private void handleKeyPress(KeyCode code) {
        player.handleMovement(code);
    }
}
