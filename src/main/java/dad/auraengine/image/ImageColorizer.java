package dad.auraengine.image;

import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ImageColorizer {

    public ImageView colorizeImage(ImageView imageView, Color color) {
        ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1);

        Blend blush = new Blend(
                BlendMode.MULTIPLY,
                monochrome,
                new ColorInput(
                        0,
                        0,
                        imageView.getImage().getWidth(),
                        imageView.getImage().getHeight(),
                        color
                )
        );

        imageView.setEffect(blush);
        return imageView;
    }
}
