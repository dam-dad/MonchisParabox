package dad.auraengine.entities;

import dad.auraengine.entities.movements.Location;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 * Clase abstracta Entity es la base para crear entidades dentro del juego, como jugadores, NPC o tiles.
 * Define propiedades comunes a todas las entidades, como tamaño, imagen y métodos para mostrarlas en el juego y actualizarlas.
 */
public abstract class Entity<T extends Shape> {

    /** Escala predeterminada para todas las entidades. */
    public static final double SCALE = 0.43;

    /** Ubicación de la entidad en el juego. */
    protected Location location;

    /** Ancho de la entidad. */
    protected int width;

    /** Alto de la entidad. */
    protected int height;

    /** Imagen asociada a la entidad. */
    protected Image image;

    /**
     * Método abstracto para renderizar la entidad en el juego.
     */
    public abstract void render();

    /**
     * Método abstracto para obtener la forma de colisión de la entidad.
     *
     * @return La forma de colisión de la entidad.
     */
    public abstract T getCollisionShape();

    /**
     * Método abstracto para destruir la entidad en el juego.
     */
    public abstract void destroy();

    /**
     * Comprueba si la entidad colisiona con otra entidad.
     *
     * @param locChecked La ubicación de la entidad con la que se comprueba la colisión.
     * @return true si hay una colisión, false de lo contrario.
     */
    public boolean checkCollision(Location locChecked) {
        return getLocation().getX() == locChecked.getX() && getLocation().getY() == locChecked.getY();
    }

    /**
     * Obtiene la imagen asociada a la entidad.
     *
     * @return La imagen asociada a la entidad.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Obtiene la ubicación de la entidad.
     *
     * @return La ubicación de la entidad.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Establece la ubicación de la entidad.
     *
     * @param location La nueva ubicación de la entidad.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Cancela el movimiento de la entidad y la restaura a su última ubicación válida.
     */
    public void cancelMove() {
        location.setX(location.getLastX());
        location.setY(location.getLastY());
    }
}
