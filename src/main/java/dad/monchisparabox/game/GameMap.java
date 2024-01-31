package dad.monchisparabox.game;

import java.util.ArrayList;
import java.util.List;

import dad.auraengine.Map;
import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.entities.StaticBlock;

public class GameMap extends Map {
	
	private Location start;
	private Location end;
	
	private List<Location> endCages = new ArrayList<>();
	private List<StaticBlock> blocks = new ArrayList<>();
	
	public GameMap(int maxX, int maxY, double scale) {
		super(maxX, maxY, scale);
		setGridLinesVisible(true);
	}
	
	public Location getStart() {
		return start;
	}
	
	public void setStart(Location start) {
		this.start = start;
	}
	
	public Location getEnd() {
		return end;
	}
	
	public void setEnd(Location end) {
		this.end = end;
	}
	
	public List<StaticBlock> getBlocks() {
		return blocks;
	}
	
	public List<Location> getEndCages() {
		return endCages;
	}
}
