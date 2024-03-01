package dad.monchisparabox.game.block;

import dad.auraengine.entities.CollidableEntity;
import dad.auraengine.entities.Entity;
import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.entities.Player;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Block extends CollidableEntity {

	public Block(Location location) {
		super(new Image("/assets/block/block.png"), location);
	}

	public void push(KeyCode keyCode) {
		location.setLastX(location.getX());
		location.setLastY(location.getY());

		boolean move = false;

		switch (keyCode) {
			case A:
				move = location.decrementX();
				break;
			case D:
				move = location.incrementX();
				break;
			case W:
				move = location.decrementY();
				break;
			case S:
				move = location.incrementY();
				break;
			default:
				break;
		}

		if(move) {
			Block block = location.getMap().getBlockAt(location, this);
			if (block != null) {
				block.handleCollision(this, keyCode);
			}
		} else {
			System.out.println("Esta saliendo del mapa mi rey");
			//TODO Sacar el bloque y traer el de atrás
		}
	}

	public void handleCollision(Entity entity, KeyCode keyCode) {
		if (checkCollision(entity.getLocation())) {
			if (this instanceof LimitBlock) {
				entity.cancelMove();
			} else if (this instanceof BoxBlock boxBlock) {
                boxBlock.push(keyCode);
			} else if (this instanceof MapBlock mapBlock) {
				mapBlock.push(keyCode);
			}

			// Estoy estancado y voy a cancelar el movimiento
			if (checkCollision(entity.getLocation())) {
				// TODO ambos somos iguales
				if(entity instanceof MapBlock) {
					// TODO ME LO COMO
					destroy(); //Lo saco de este mapa
				}

				if(this instanceof MapBlock && entity instanceof BoxBlock) {
					//TODO El bloque pa dentro
					System.out.println("BLOQUE DENTRO");
					entity.destroy();
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
}
