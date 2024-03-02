package dad.monchisparabox.game.block;

import dad.auraengine.entities.movements.Location;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class BoxBlock extends Block {

    private ImageView blockView;

    public BoxBlock(Location location) {
        super(location);
        image = new Image("/assets/block/libre2.png");
        blockView = new ImageView(image);

        this.location = location;
    }

    public void updateBlock() {
        GridPane.setColumnIndex(blockView, location.getX());
        GridPane.setRowIndex(blockView, location.getY());
    }

    @Override
    public void render() {
        if(rendered) return;

        location.getMap().addEntity(blockView, location.getX(), location.getY());
        rendered = true;
    }

    @Override
    public void destroy() {
        location.getMap().getBlocks().remove(this);
        location.getMap().getChildren().remove(blockView);
        rendered = false;
    }
}
