package dad.monchisparabox.game.entities;

import dad.auraengine.entities.Entity;
import dad.auraengine.entities.movements.Direction;
import dad.monchisparabox.App;
import dad.auraengine.media.Music;
import dad.monchisparabox.game.block.Block;
import dad.monchisparabox.ui.controller.MainController;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Clase que representa al jugador en el juego Monchi's Parabox.
 */
public class Player extends Entity<Rectangle> {

    private ImageView playerComponent;
    private ScaleTransition scaleTransition;
    private Music music;

    /**
     * Constructor de la clase Player.
     */
    public Player() {
        super();
        // Cargar imagen del jugador
        image = MainController.getUserData().getSkin();

        playerComponent = new ImageView();
        playerComponent.setImage(image);
        playerComponent.setFitWidth(50);
        playerComponent.setFitHeight(50);

        // Variables de tamaño del personaje
        this.width = 50;
        this.height = 50;

        scaleTransition = new ScaleTransition(Duration.seconds(0.2), playerComponent);
        scaleTransition.setToX(0.9); // Escalar en el eje X a 0.9 (encoger un poco)
        scaleTransition.setToY(0.9); // Escalar en el eje Y a 0.9 (encoger un poco)
        scaleTransition.setAutoReverse(true);
    }

    /**
     * Renderiza al jugador en el mapa.
     */
    @Override
    public void render() {
        location.getMap().addEntity(playerComponent, location.getX(), location.getY());
    }

    /**
     * Actualiza la posición del jugador en el mapa.
     */
    public void updatePlayer() {
        GridPane.setColumnIndex(playerComponent, location.getX());
        GridPane.setRowIndex(playerComponent, location.getY());
    }

    /**
     * Obtiene la forma de colisión del jugador.
     *
     * @return La forma de colisión del jugador.
     */
    @Override
    public Rectangle getCollisionShape() {
        return new Rectangle(width, height);
    }

    /**
     * Destruye al jugador.
     */
    @Override
    public void destroy() {
        location.getMap().getChildren().remove(playerComponent);
    }

    /**
     * Maneja el movimiento del jugador en una dirección dada.
     *
     * @param direction La dirección del movimiento.
     */
    public void handleMovement(Direction direction) {
        location.setLastX(location.getX());
        location.setLastY(location.getY());
        music = new Music("MovePlayer");

        boolean move = false;

        switch (direction) {
            case LEFT:
                move = location.decrementX();
                music.playOnce();
                break;
            case RIGHT:
                move = location.incrementX();
                music.playOnce();
                break;
            case UP:
                move = location.decrementY();
                music.playOnce();
                break;
            case DOWN:
                move = location.incrementY();
                music.playOnce();
                break;
            default:
                break;
        }

        if(move) {
            Block block = location.getMap().getBlockAt(location, null);
            if (block != null) {
                block.handleCollision(this, direction);
            }
            App.getGameController().getMapController().getGame().movimientos++;
            App.getGameController().getMapController().getGame().checkWin();
        } else {
            App.getGameController().getMapController().getGame().changeMap(location.getMap().getJoinLocation().getMap(), location.getMap().getJoinLocation(), false);
        }
    }

    /**
     * Cancela el último movimiento del jugador.
     */
    public void cancelMove() {
        location.setX(location.getLastX());
        location.setY(location.getLastY());
    }
}
