package dad.monchisparabox.game;

import java.util.ArrayList;
import java.util.List;

import dad.auraengine.Map;
import dad.auraengine.entities.StaticEntity;
import dad.auraengine.entities.movements.Direction;
import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.block.Block;
import dad.monchisparabox.game.block.BoxBlock;
import dad.monchisparabox.game.block.MapBlock;
import javafx.scene.input.KeyCode;

public class GameMap extends Map {
	
	private Location start;
	private Location end;
	
	private List<Location> endCages = new ArrayList<>();
	private List<Block> blocks = new ArrayList<>();
	
	public GameMap(int maxX, int maxY, double scale) {
		super(maxX, maxY, scale);
		setGridLinesVisible(true);
	}

	public void load() {
		getBlocks().forEach(StaticEntity::render);
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
			if(block instanceof BoxBlock boxBlock) {
                boxBlock.updateBlock();
			}
			if(block instanceof MapBlock mapBlock) {
				mapBlock.updateBlock();
			}
		}
	}

	public Direction getFacing() {
		if(getStart().getX() == 0 && getStart().getX() < getMapMaxWidth()-1 && getStart().getY() > 0 && getStart().getY() < getMapMaxHeight()-1) {
			return Direction.LEFT;
		} else if(getStart().getX() == getMapMaxWidth()-1 && getStart().getY() > 0 && getStart().getY() < getMapMaxHeight()-1) {
			return Direction.RIGHT;
		} else if(getStart().getX() < getMapMaxWidth()-1 && getStart().getX() > 0 && getStart().getY() == 0) {
			return Direction.UP;
		} else if(getStart().getX() < getMapMaxWidth()-1 && getStart().getX() > 0 && getStart().getY() == getMapMaxHeight()-1) {
			return Direction.DOWN;
		}
		return null;
	}

	public Block getAdjacentBlock(Location location, KeyCode keyCode) {
		int adjacentX = (keyCode == KeyCode.A) ? location.getX() - 1 :
				(keyCode == KeyCode.D) ? location.getX() + 1 : location.getX();
		int adjacentY = (keyCode == KeyCode.W) ? location.getY() - 1 :
				(keyCode == KeyCode.S) ? location.getY() + 1 : location.getY();

		// Obtener la lista completa de bloques en el mapa
		List<Block> blocks = location.getMap().getBlocks();

		// Buscar el bloque adyacente en la lista de bloques
		for (Block block : blocks) {
			if (block.getLocation().getX() == adjacentX && block.getLocation().getY() == adjacentY) {
				return block;
			}
		}

		// Si no se encuentra, devolver null
		return null;
	}

	public Block getBlockAt(Location location, Block blockToIgnore) {
		// Buscar el bloque adyacente en la lista de bloques
		for (Block block : blocks) {
			if(block != blockToIgnore) {
				if (block.getLocation().getX() == location.getX() && block.getLocation().getY() == location.getY()) {
					return block;
				}
			}
		}

		// Si no se encuentra, devolver null
		return null;
	}
}
