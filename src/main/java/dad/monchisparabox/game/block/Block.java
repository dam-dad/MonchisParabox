package dad.monchisparabox.game.block;

import dad.auraengine.entities.CollidableEntity;
import dad.auraengine.entities.Entity;
import dad.auraengine.entities.movements.Direction;
import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.App;
import dad.monchisparabox.game.entities.Player;
import javafx.scene.image.Image;

/**
 * Clase que representa un bloque en el juego Monchi's Parabox.
 */
public class Block extends CollidableEntity {

	/**
     * Constructor de la clase Block.
     *
     * @param location La ubicación del bloque.
     */
    public Block(Location location) {
        super(new Image("/assets/block/block.png"), location);
    }

    /**
     * Método para empujar el bloque en una dirección determinada.
     *
     * @param direction La dirección en la que se empuja el bloque.
     */
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

        if (move) {
            Block block = location.getMap().getBlockAt(location, this);
            if (block != null) {
                block.handleCollision(this, direction);
            }
        } else {
            if(!App.getGameController().getMapController().getGame().teleportBlockToMap(location.getMap().getJoinLocation().getMap(),
                    true, this, location.getMap().getJoinLocation())) {
                cancelMove();
            }
        }
    }

    /**
     * Maneja la colisión del bloque con otra entidad.
     *
     * @param entity     La entidad con la que se produce la colisión.
     * @param direction  La dirección en la que se produce la colisión.
     */
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
                // Si soy un mapblock y me choca un bloque
                if (this instanceof MapBlock mapBlock && entity instanceof Block block && !(entity instanceof LimitBlock)) {
                    if (mapBlock.getGameMap().getFacing() == Direction.UP && direction == Direction.DOWN) {
                        if (!App.getGameController().getMapController().getGame().teleportBlockToMap(mapBlock.getGameMap(), false, block, mapBlock.getGameMap().getStart())) {
                            block.cancelMove();
                        }
                        return;
                    } else if (mapBlock.getGameMap().getFacing() == Direction.DOWN && direction == Direction.UP) {
                        if (!App.getGameController().getMapController().getGame().teleportBlockToMap(mapBlock.getGameMap(), false, block, mapBlock.getGameMap().getStart())) {
                            block.cancelMove();
                        }
                        return;
                    } else if (mapBlock.getGameMap().getFacing() == Direction.LEFT && direction == Direction.RIGHT) {
                        if (!App.getGameController().getMapController().getGame().teleportBlockToMap(mapBlock.getGameMap(), false, block, mapBlock.getGameMap().getStart())) {
                            block.cancelMove();
                        }
                        return;
                    } else if (mapBlock.getGameMap().getFacing() == Direction.RIGHT && direction == Direction.LEFT) {
                        if (!App.getGameController().getMapController().getGame().teleportBlockToMap(mapBlock.getGameMap(), false, block, mapBlock.getGameMap().getStart())) {
                            block.cancelMove();
                        }
                        return;
                    }
                }

                // Si soy un bloque y me choca un mapblock
                if (entity instanceof MapBlock mapBlock) {
                    if (mapBlock.getGameMap().getFacing() == Direction.UP) {
                        if (direction == Direction.UP) {
                            if (!App.getGameController().getMapController().getGame().teleportBlockToMap(mapBlock.getGameMap(), false, this, mapBlock.getGameMap().getStart())) {
                                entity.cancelMove();
                            }
                        } else {
                            entity.cancelMove();
                        }
                    } else if (mapBlock.getGameMap().getFacing() == Direction.DOWN) {
                        if (direction == Direction.DOWN) {
                            if (!App.getGameController().getMapController().getGame().teleportBlockToMap(mapBlock.getGameMap(), false, this, mapBlock.getGameMap().getStart())) {
                                entity.cancelMove();
                            }
                        } else {
                            entity.cancelMove();
                        }
                    } else if (mapBlock.getGameMap().getFacing() == Direction.LEFT) {
                        if (direction == Direction.LEFT) {
                            if (!App.getGameController().getMapController().getGame().teleportBlockToMap(mapBlock.getGameMap(), false, this, mapBlock.getGameMap().getStart())) {
                                entity.cancelMove();
                            }
                        } else {
                            entity.cancelMove();
                        }
                    } else if (mapBlock.getGameMap().getFacing() == Direction.RIGHT) {
                        if (direction == Direction.RIGHT) {
                            if (!App.getGameController().getMapController().getGame().teleportBlockToMap(mapBlock.getGameMap(), false, this, mapBlock.getGameMap().getStart())) {
                                entity.cancelMove();
                            }
                        } else {
                            entity.cancelMove();
                        }
                    }
                }

                if (!(entity instanceof MapBlock)) {
                    // Soy un jugador y me choco con un mapblock
                    if (this instanceof MapBlock mapBlock && entity instanceof Player) {
                        if (mapBlock.getGameMap().getFacing() == Direction.UP && direction == Direction.DOWN) {
                            App.getGameController().getMapController().getGame().changeMap(mapBlock.getGameMap(), mapBlock.getGameMap().getStart(), true);
                        } else if (mapBlock.getGameMap().getFacing() == Direction.DOWN && direction == Direction.UP) {
                            App.getGameController().getMapController().getGame().changeMap(mapBlock.getGameMap(), mapBlock.getGameMap().getStart(), true);
                        } else if (mapBlock.getGameMap().getFacing() == Direction.LEFT && direction == Direction.RIGHT) {
                            App.getGameController().getMapController().getGame().changeMap(mapBlock.getGameMap(), mapBlock.getGameMap().getStart(), true);
                        } else if (mapBlock.getGameMap().getFacing() == Direction.RIGHT && direction == Direction.LEFT) {
                            App.getGameController().getMapController().getGame().changeMap(mapBlock.getGameMap(), mapBlock.getGameMap().getStart(), true);
                        }
                    }

                    entity.cancelMove();
                }
            }
        }
    }
}
