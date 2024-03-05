package dad.auraengine.entities;

import dad.auraengine.entities.movements.Location;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

/**
 * La clase StaticEntity representa una entidad estática en el juego, como un tile de suelo.
 * Utiliza un método getShape para comprobar colisiones con el jugador.
 */
public class StaticEntity<T extends Shape> extends Entity<T> {

    /** Vista de imagen asociada a la entidad. */
    protected ImageView imageView;

    /** Indica si la entidad ha sido renderizada en el juego. */
    protected boolean rendered = false;

    /**
     * Crea una nueva instancia de StaticEntity con la imagen y la ubicación especificadas.
     *
     * @param image     La imagen asociada a la entidad.
     * @param location  La ubicación de la entidad.
     */
    public StaticEntity(Image image, Location location) {
        super();
        this.image = image;
        this.location = location;
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
    }

    /**
     * Renderiza la entidad en el juego.
     */
    @Override
    public void render() {
        if (rendered) return;

        imageView = new ImageView(image);
        location.getMap().addEntity(imageView, location.getX(), location.getY());
        rendered = true;
    }

    /**
     * Obtiene la forma de colisión de la entidad.
     *
     * @return La forma de colisión de la entidad.
     */
    @Override
    public T getCollisionShape() {
        return null;
    }

    /**
     * Destruye la entidad en el juego.
     */
    @Override
    public void destroy() {
        if (imageView != null) {
            location.getMap().getChildren().remove(imageView);
            rendered = false;
        }
    }

}
