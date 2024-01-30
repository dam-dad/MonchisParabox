package dad.monchisparabox.game;

import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.entities.Player;
import dad.monchisparabox.game.utilities.Tile;

public class Game {

	private Player player;
	
	private GameMap gameMap;
	
	public Game(String tiledMap) {
		this.gameMap = Tile.tile(tiledMap);
		
		this.player = new Player();
		player.setLocation(new Location(gameMap, 0, 0));
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public GameMap getGameMap() {
		return gameMap;
	}
}
