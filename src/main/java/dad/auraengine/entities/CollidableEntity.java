package dad.auraengine.entities;

import dad.auraengine.entities.movements.Location;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * Clase CollidableEntity representa una entidad colisionable en el juego.
 * Esta clase extiende la clase StaticEntity y proporciona un objeto de transición visualmente
 * similar a un tile de suelo. Se utiliza el método getShape para comprobar colisiones con el jugador.
 */
public class CollidableEntity extends StaticEntity<Rectangle> {

    /**
     * Constructor de la clase CollidableEntity.
     *
     * @param image    La imagen asociada a la entidad colisionable.
     * @param location La ubicación de la entidad colisionable en el juego.
     */
    public CollidableEntity(Image image, Location location) {
        super(image, location);
    }

    /**
     * Obtiene la forma de colisión de la entidad.
     *
     * @return La forma de colisión de la entidad, en este caso un rectángulo con el ancho y alto de la entidad.
     */
    @Override
    public Rectangle getCollisionShape() {
        return new Rectangle(width, height);
    }
}
