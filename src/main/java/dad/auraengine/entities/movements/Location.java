package dad.auraengine.entities.movements;

import dad.auraengine.Map;

public class Location {
	
	private Map map;
	private int X;
	private int Y;
	
	private Location() {}
	
	public Location(Map map, int X, int Y) {
		this.map = map;
		this.X = X;
		this.Y = Y;
	}
	
	public Map getMap() {
		return map;
	}
	
	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
}
