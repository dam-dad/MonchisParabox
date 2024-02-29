package dad.monchisparabox.game.entities;

import dad.auraengine.entities.movements.Location;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

public class BoxBlock extends Block {

	private ImageView blockView;

	public BoxBlock(Location location) {
		super(location);
		image = new Image("/assets/block/libre5.png");
		blockView = new ImageView(image);

		this.location = location;
	}

	public void empujar(KeyCode keyCode) {
		getLocation().setLastX(getLocation().getX());
		getLocation().setLastY(getLocation().getY());
		
		switch (keyCode) {
		case A:
			location.decrementX();
			break;
		case D:
			location.incrementX();
			break;
		case W:
			location.decrementY();
			break;
		case S:
			location.incrementY();
			break;
		default:
			break;
		}

		for (Block block : getLocation().getMap().getBlocks()) {
			if (block != this) {
				if (block.checkCollision(getLocation())) {
					if (block instanceof BoxBlock) {
						BoxBlock boxBlock = (BoxBlock) block;
						boxBlock.empujar(keyCode);
					} else {
						getLocation().setX(getLocation().getLastX());
						getLocation().setY(getLocation().getLastY());
					}
				}
			}
		}
	}

	@Override
	public void render() {
		location.getMap().addEntity(blockView, location.getX(), location.getY());
	}

	public void updateBlock() {
		GridPane.setColumnIndex(blockView, location.getX());
		GridPane.setRowIndex(blockView, location.getY());
	}
}
