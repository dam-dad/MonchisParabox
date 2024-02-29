package dad.monchisparabox.game;

import java.util.ArrayList;
import java.util.List;

import dad.auraengine.Map;
import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.entities.Block;
import dad.monchisparabox.game.entities.BoxBlock;

public class GameMap extends Map {
	
	private Location start;
	private Location end;
	
	private List<Location> endCages = new ArrayList<>();
	private List<Block> blocks = new ArrayList<>();
	
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
	
	public List<Block> getBlocks() {
		return blocks;
	}
	
	public List<Location> getEndCages() {
		return endCages;
	}
	
	public void handleMap() {
		for(Block block : getBlocks()) {
			if(block instanceof BoxBlock) {
				BoxBlock boxBlock = (BoxBlock) block;
				boxBlock.updateBlock();
			}
		}
	}
}
