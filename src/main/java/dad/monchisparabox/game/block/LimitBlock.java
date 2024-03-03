package dad.monchisparabox.game.block;

import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.GameMap;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class LimitBlock extends Block {

	public LimitBlock(Location location) {
		super(location);
	}
	private List<Location> endCages = new ArrayList<>();
	private List<Block> blocks = new ArrayList<>();



	public void  setImage(Image image){
		this.image = image;
		this.imageView.setImage(image);
	}


	
}
