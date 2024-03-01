package dad.monchisparabox.game.block;

import dad.auraengine.entities.CollidableEntity;
import dad.auraengine.entities.Entity;
import dad.auraengine.entities.movements.Direction;
import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.entities.Player;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Block extends CollidableEntity {

	public Block(Location location) {
		super(new Image("/assets/block/block.png"), location);
	}

	public void push(Direction direction) {
		location.setLastX(location.getX());
		location.setLastY(location.getY());

		boolean move = false;

		switch (direction) {
			case LEFT:
				move = location.decrementX();
				break;
			case RIGHT:
				move = location.incrementX();
				break;
			case UP:
				move = location.decrementY();
				break;
			case DOWN:
				move = location.incrementY();
				break;
			default:
				break;
		}

		if(move) {
			Block block = location.getMap().getBlockAt(location, this);
			if (block != null) {
				block.handleCollision(this, direction);
			}
		} else {
			System.out.println("Esta saliendo del mapa mi rey");
			//TODO Sacar el bloque y traer el de atrás
		}
	}

	public void handleCollision(Entity entity, Direction direction) {
		if (checkCollision(entity.getLocation())) {
			if (this instanceof LimitBlock) {
				entity.cancelMove();
			} else if (this instanceof BoxBlock boxBlock) {
                boxBlock.push(direction);
			} else if (this instanceof MapBlock mapBlock) {
				mapBlock.push(direction);
			}

			// Estoy estancado y voy a cancelar el movimiento
			if (checkCollision(entity.getLocation())) {

			/*	// si soy un bloque y me choca un mapblock
				if(entity instanceof MapBlock mapBlock) {
					if(direction == Direction.LEFT) {
						// TODO ME LO COMO
						destroy(); //Lo saco de este mapa
					} else {
						entity.cancelMove();
					}
				}
			 */
				if(entity instanceof MapBlock mapBlock) {
					if(mapBlock.getFacing() == Direction.UP) {
						if(direction == Direction.UP) {
							destroy();
						} else {
							entity.cancelMove();
						}
					} else if (mapBlock.getFacing() == Direction.DOWN) {
						if(direction == Direction.DOWN) {
							destroy();
						} else {
							entity.cancelMove();
						}
					} else if (mapBlock.getFacing() == Direction.LEFT) {
						if(direction == Direction.LEFT) {
							destroy();
						} else {
							entity.cancelMove();
						}
					} else if (mapBlock.getFacing() == Direction.RIGHT) {
						if(direction == Direction.RIGHT) {
							destroy();
						} else {
							entity.cancelMove();
						}
					}
				}


				// Si soy un mapblock y me choca un bloque
				if(this instanceof MapBlock mapBlock && entity instanceof BoxBlock) {
					if(mapBlock.getFacing() == Direction.UP) {
						if(direction == Direction.DOWN) {
							entity.destroy();
						}
					} else if (mapBlock.getFacing() == Direction.DOWN) {
						if(direction == Direction.UP) {
							entity.destroy();
						}
					} else if (mapBlock.getFacing() == Direction.LEFT) {
						if(direction == Direction.RIGHT) {
							entity.destroy();
						}
					} else if (mapBlock.getFacing() == Direction.RIGHT) {
						if(direction == Direction.LEFT) {
							entity.destroy();
						}
					}
					return;
				}

				if(!(entity instanceof MapBlock)) {
					if (this instanceof MapBlock && entity instanceof Player) {
						//TODO El jugador pa dentro
						System.out.println("JUGADOR DENTRO");
					}

					entity.cancelMove();
				}
			}
		}
	}

	public void handleKick(Entity entity, Direction direction) {
		if(direction == Direction.RIGHT) {
			System.out.println("BLOQUE DENTRO");
			entity.destroy();
		} else {
			entity.cancelMove();
		}
	}
}
