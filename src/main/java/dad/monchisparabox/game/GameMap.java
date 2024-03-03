package dad.monchisparabox.game;

import java.util.ArrayList;
import java.util.List;

import dad.auraengine.Map;
import dad.auraengine.entities.StaticEntity;
import dad.auraengine.entities.movements.Direction;
import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.block.Block;
import dad.monchisparabox.game.block.BoxBlock;
import dad.monchisparabox.game.block.LimitBlock;
import dad.monchisparabox.game.block.MapBlock;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class GameMap extends Map {

    private Location start;
    private Location end;

    private List<Location> endCages = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();

    private Location joinLocation;

    public GameMap(int maxX, int maxY, double scale) {
        super(maxX, maxY, scale);
        setGridLinesVisible(true);
    }

    public void load() {
        getBlocks().forEach(StaticEntity::render);
        assignBlockImages();
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

    public Location getJoinLocation() {
        return joinLocation;
    }

    public void setJoinLocation(Location joinLocation) {
        this.joinLocation = joinLocation;
    }

    public void handleMap() {
        for (Block block : getBlocks()) {
            if (block instanceof BoxBlock boxBlock) {
                boxBlock.updateBlock();
            }
            if (block instanceof MapBlock mapBlock) {
                mapBlock.updateBlock();
            }
        }
    }

    public Direction getFacing() {
        if (getStart().getX() == 0 && getStart().getX() < getMapMaxWidth() - 1 && getStart().getY() > 0 && getStart().getY() < getMapMaxHeight() - 1) {
            return Direction.LEFT;
        } else if (getStart().getX() == getMapMaxWidth() - 1 && getStart().getY() > 0 && getStart().getY() < getMapMaxHeight() - 1) {
            return Direction.RIGHT;
        } else if (getStart().getX() < getMapMaxWidth() - 1 && getStart().getX() > 0 && getStart().getY() == 0) {
            return Direction.UP;
        } else if (getStart().getX() < getMapMaxWidth() - 1 && getStart().getX() > 0 && getStart().getY() == getMapMaxHeight() - 1) {
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
            if (block != blockToIgnore) {
                if (block.getLocation().getX() == location.getX() && block.getLocation().getY() == location.getY()) {
                    return block;
                }
            }
        }

        // Si no se encuentra, devolver null
        return null;
    }
  
  public  Block getBlockAtForLimitBlock(Location location) {
		// Buscar el bloque adyacente en la lista de bloques
		for (Block block : blocks) {
			if(block instanceof LimitBlock limitBlock) {
				if (block.getLocation().getX() == location.getX() && block.getLocation().getY() == location.getY()) {
					return block;
				}
			}
		}

		// Si no se encuentra, devolver null
		return null;
	}



	public void assignBlockImages() {
		for (Block block : blocks) {
			if(block instanceof LimitBlock limitBlock) {
				int x = block.getLocation().getX();
				int y = block.getLocation().getY();

				// Obtener los bloques adyacentes
				Block leftBlock = getBlockAtForLimitBlock(new Location(this,x - 1, y));
				Block rightBlock = getBlockAtForLimitBlock(new Location(this,x + 1, y));
				Block topBlock = getBlockAtForLimitBlock(new Location(this,x, y - 1));
				Block bottomBlock = getBlockAtForLimitBlock(new Location(this,x, y + 1));

				// Determinar el tipo de bloque de alrededor
				boolean hasLeftBlock = leftBlock != null;
				boolean hasRightBlock = rightBlock != null;
				boolean hasTopBlock = topBlock != null;
				boolean hasBottomBlock = bottomBlock != null;

				// Asignar imagen según el tipo de bloque de alrededor
				if (hasLeftBlock && hasRightBlock && hasTopBlock && hasBottomBlock) {
					//hecho
					System.out.println("a");
					limitBlock.setImage(new Image("/assets/block/center_block.png"));
				}else if(!hasRightBlock && hasBottomBlock && hasTopBlock && hasLeftBlock) {
                    System.out.println("n");
                    limitBlock.setImage(new Image("/assets/block/rightVoid.png"));

                }else if(hasRightBlock && hasBottomBlock && hasTopBlock && !hasLeftBlock) {
                    System.out.println("n");
                    limitBlock.setImage(new Image("/assets/block/leftVoid.png"));}
                else if (hasLeftBlock && hasRightBlock && hasTopBlock) {
					//hecho
					System.out.println("b");
					limitBlock.setImage(new Image("/assets/block/bottom_block.png"));
				} else if (hasLeftBlock && hasRightBlock && hasBottomBlock) {
					//hecho
					System.out.println("c");
					limitBlock.setImage(new Image("/assets/block/top_block.png"));
				} else if (hasLeftBlock && hasRightBlock) {
					//hecho
					System.out.println("d");
					limitBlock.setImage(new Image("/assets/block/horizontal_block.png"));
				} else if (hasTopBlock && hasBottomBlock) {
					//hecho
					System.out.println("e");
					limitBlock.setImage(new Image("/assets/block/vertical_block.png"));
				} else if (hasLeftBlock && hasTopBlock) {

					System.out.println("f");
					limitBlock.setImage(new Image("/assets/block/bottom_right_block.png"));
				} else if (hasLeftBlock && hasBottomBlock) {
					//hecho
					System.out.println("g");

					limitBlock.setImage(new Image("/assets/block/top_right_block.png"));
				} else if (hasRightBlock && hasTopBlock) {
					//hecho
					System.out.println("h");
					limitBlock.setImage(new Image("/assets/block/bottom_left_block.png"));
				} else if (hasRightBlock && hasBottomBlock) {
					//hecho
					System.out.println("i");
					limitBlock.setImage(new Image("/assets/block/top_left_block.png"));
					
				}else if (hasLeftBlock && !hasRightBlock && !hasTopBlock && !hasBottomBlock) {
                    System.out.println("j");
					limitBlock.setImage(new Image("/assets/block/right_edge_block.png"));
				} else if (!hasLeftBlock && hasRightBlock && !hasTopBlock && !hasBottomBlock) {
                    System.out.println("k");
					limitBlock.setImage(new Image("/assets/block/left_edge_block.png"));
				} else if (!hasLeftBlock && !hasRightBlock && hasTopBlock && !hasBottomBlock) {
                    System.out.println("l");
					limitBlock.setImage(new Image("/assets/block/bottom_edge_block.png"));
				} else if (!hasLeftBlock && !hasRightBlock && !hasTopBlock && hasBottomBlock) {
                    System.out.println("m");
					limitBlock.setImage(new Image("/assets/block/top_edge_block.png"));
				}else {
					//hecho
					System.out.println("j");
					limitBlock.setImage(new Image("/assets/block/lonely_block.png"));
				}
			}
		}
	}
}
