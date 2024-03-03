package dad.auraengine.entities.movements;

import dad.monchisparabox.game.GameMap;

public class Location {
	
	private GameMap map;
	private int X;
	private int Y;
	
	private int lastX;
	private int lastY;
	private Location() {}
	
	public Location(GameMap map, int X, int Y) {
		this.map = map;
		this.X = X;
		this.Y = Y;
	}
	
	public GameMap getMap() {
		return map;
	}
	
	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
	
	public int getLastX() {
		return lastX;
	}
	
	public int getLastY() {
		return lastY;
	}
	
	public void setLastX(int lastX) {
		this.lastX = lastX;
	}
	
	public void setLastY(int lastY) {
		this.lastY = lastY;
	}
	
	public void setX(int x) {
		X = x;
	}
	
	public void setY(int y) {
		Y = y;
	}
	
	public boolean incrementX() {
		if (X < map.getMapMaxWidth() - 1) {
			X++;
			return true;
		}
		return false;
	}
	
	public boolean incrementY() {
		if (Y < map.getMapMaxHeight() - 1) {
			Y++;
			return true;
		}
		return false;
	}
	
	public boolean decrementX() {
		if (X > 0) {
			X--;
			return true;
		}
		return false;
	}
	
	public boolean decrementY() {
		if (Y > 0) {
			Y--;
			return true;
		}
		return false;
	}
}
