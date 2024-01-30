package dad.monchisparabox.game.entities;

import dad.auraengine.entities.CollidableEntity;
import dad.auraengine.entities.movements.Location;
import javafx.scene.image.Image;

public class StaticBlock extends CollidableEntity {

	public StaticBlock(Location location) {
		super(new Image("/assets/block/{AQUIVALAIMAGEN}.png"), location);
	}
	
}
