package dad.monchisparabox.game.block;

import dad.auraengine.entities.movements.Location;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Clase que representa un bloque de caja en el juego Monchi's Parabox.
 */
public class BoxBlock extends Block {

    private ImageView blockView;

    /**
     * Constructor de la clase BoxBlock.
     *
     * @param location La ubicación del bloque de caja.
     */
    public BoxBlock(Location location) {
        super(location);
        image = new Image("/assets/entities/caja.png");
        blockView = new ImageView(image);

        this.location = location;
    }

    /**
     * Actualiza la posición del bloque de caja en la interfaz gráfica.
     */
    public void updateBlock() {
        GridPane.setColumnIndex(blockView, location.getX());
        GridPane.setRowIndex(blockView, location.getY());
    }

    /**
     * Renderiza el bloque de caja en la interfaz gráfica.
     */
    @Override
    public void render() {
        if (rendered) return;

        location.getMap().addEntity(blockView, location.getX(), location.getY());
        rendered = true;
    }

    /**
     * Destruye el bloque de caja, eliminándolo de la lista de bloques y de la interfaz gráfica.
     */
    @Override
    public void destroy() {
        location.getMap().getBlocks().remove(this);
        location.getMap().getChildren().remove(blockView);
        rendered = false;
    }
}
