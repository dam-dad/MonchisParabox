package dad.monchisparabox.game.entities;

import dad.auraengine.entities.CollidableEntity;
import dad.auraengine.entities.movements.Location;
import javafx.scene.image.Image;

public class Block extends CollidableEntity {

	public Block(Location location) {
		super(new Image("/assets/block/block.png"), location);
	}
	
}
