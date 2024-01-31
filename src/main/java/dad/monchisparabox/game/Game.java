package dad.monchisparabox.game;

import java.util.ArrayList;

import dad.auraengine.entities.movements.Direction;
import dad.monchisparabox.game.entities.Player;
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
		root.setOnKeyReleased(event -> {
			player.setDirection(Direction.NONE);
		});
		
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	            root.requestFocus();
	        }
	    });
		
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
		player.movePlayer();	
	}

	private void handleKeyPress(KeyCode code) {
		switch (code) {
		case LEFT:
			player.setDirection(Direction.WEST);
			break;
		case RIGHT:
			player.setDirection(Direction.EAST);
			break;
		case UP:
			player.setDirection(Direction.NORTH);
			break;
		case DOWN:
			player.setDirection(Direction.SOUTH);
			break;
		default:
			break;
		}
	}
}
