package dad.monchisparabox.game;

import java.util.ArrayList;


import dad.monchisparabox.game.entities.Player;
import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.entities.Block;
import dad.monchisparabox.game.entities.BoxBlock;
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

	
	//TODO What a mierdon!!! Mejorar...
	private void handleKeyPress(KeyCode code) {
		player.getLocation().setLastX(player.getLocation().getX());
		player.getLocation().setLastY(player.getLocation().getY());
		
		switch (code) {
		case A:
			player.getLocation().decrementX();
			break;
		case D:
			player.getLocation().incrementX();
			break;
		case W:
			player.getLocation().decrementY();
			break;
		case S:
			player.getLocation().incrementY();
			break;
		default:
			break;
		}
		
		for(Block block : player.getLocation().getMap().getBlocks()) {
			if (block.checkCollision(player.getLocation())) {
				if(block instanceof BoxBlock) {
					BoxBlock boxBlock = (BoxBlock) block;
					boxBlock.empujar(code);
				} else {
					player.getLocation().setX(player.getLocation().getLastX());
			        player.getLocation().setY(player.getLocation().getLastY());
				}
		    }
		}
		
		
	}
}
