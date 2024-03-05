package dad.auraengine.image;

import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Clase que proporciona métodos para cambiar el color de una imagen en un ImageView.
 */
public class ImageColorizer {

    /**
     * Aplica un efecto de color a una imagen en un ImageView.
     * @param imageView el ImageView que contiene la imagen.
     * @param color el color que se aplicará a la imagen.
     * @return el ImageView con el efecto de color aplicado.
     */
    public ImageView colorizeImage(ImageView imageView, Color color) {
        // Crear efecto de desaturación
        ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1);

        // Crear mezcla para aplicar el color
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

        // Aplicar el efecto de color al ImageView
        imageView.setEffect(blush);
        return imageView;
    }
}
