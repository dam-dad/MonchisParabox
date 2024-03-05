package dad.monchisparabox.game.block;

import dad.auraengine.entities.movements.Location;
import javafx.scene.image.Image;

/**
 * Clase que representa un bloque límite en el juego Monchi's Parabox.
 */
public class LimitBlock extends Block {

    /**
     * Constructor de la clase LimitBlock.
     *
     * @param location La ubicación del bloque límite.
     */
    public LimitBlock(Location location) {
        super(location);
    }

    /**
     * Establece la imagen del bloque límite y la asigna a su vista de imagen.
     *
     * @param image La imagen a establecer.
     */
    public void setImage(Image image) {
        this.image = image;
        this.imageView.setImage(image);
    }
}
