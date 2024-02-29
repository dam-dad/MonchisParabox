package dad.monchisparabox.game;

import java.util.ArrayList;


import dad.monchisparabox.game.block.LimitBlock;
import dad.monchisparabox.game.entities.Player;
import dad.monchisparabox.game.block.Block;
import dad.monchisparabox.game.block.BoxBlock;
import dad.monchisparabox.game.utilities.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class Game extends AnimationTimer {

    private Player player;

    private ArrayList<GameMap> maps = new ArrayList<>();

    public Game(Node root, String tiledMap) {
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
        maps.get(0).getBlocks().forEach(t -> t.render());
        player.render();
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
