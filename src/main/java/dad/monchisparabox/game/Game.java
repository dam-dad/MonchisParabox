package dad.monchisparabox.game;

import dad.auraengine.entities.movements.Direction;
import dad.auraengine.entities.movements.Location;
import dad.auraengine.media.Music;
import dad.monchisparabox.App;
import dad.monchisparabox.game.block.Block;
import dad.monchisparabox.game.data.MapData;
import dad.monchisparabox.game.data.StatsController;
import dad.monchisparabox.game.entities.Player;
import dad.monchisparabox.ui.controller.MainController;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.time.Instant;
import java.util.ArrayList;

/**
 * Clase que controla la lógica principal del juego Monchi's Parabox.
 */
public class Game extends AnimationTimer {

    private Player player;
    private ArrayList<GameMap> maps = new ArrayList<>();
    private Music music;

    private MapData mapData;

    private Music musicaAumento = new Music("AumentoCaja");
    private Music musicaDecrecer = new Music("DecrecerCaja");

    private Instant start;
    public int movimientos;

    /**
     * Constructor de la clase Game.
     *
     * @param mapData Datos del mapa del juego.
     */
    public Game(MapData mapData) {
        this.mapData = mapData;
        maps.addAll(mapData.getGameMaps());

        player = new Player();

        player.setLocation(maps.get(0).getStart());

        music = new Music(mapData.getCancion());

        music.play(MainController.getUserData().getVolumen() / 100.0);

        App.getGameController().getView().setOnKeyPressed(event -> handleKeyPress(event.getCode()));

        Platform.runLater(App.getGameController().getView()::requestFocus);

        init();
    }

    /**
     * Obtiene el jugador actual.
     *
     * @return El jugador actual.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Obtiene el primer mapa del juego.
     *
     * @return El primer mapa del juego.
     */
    public GameMap getInitialMap() {
        return maps.get(0);
    }

    /**
     * Inicializa el juego.
     */
    public void init() {
        getInitialMap().load();
        player.render();
        start = Instant.now();
    }

    /**
     * Cambia el mapa actual.
     *
     * @param gameMap Mapa al que se cambiará.
     * @param location Nueva ubicación.
     * @param joining  Booleano que indica si se está uniendo o no.
     */
    public void changeMap(GameMap gameMap, Location location, boolean joining) {
        // Implementación omitida por brevedad
    }

    /**
     * Teletransporta un bloque a un mapa específico.
     *
     * @param gameMapTo Mapa al que se teleportará el bloque.
     * @param kicking   Booleano que indica si se está pateando o no.
     * @param block     Bloque a teletransportar.
     * @param location  Nueva ubicación del bloque.
     * @return True si se pudo teleportar el bloque, false en caso contrario.
     */
    public boolean teleportBlockToMap(GameMap gameMapTo, boolean kicking, Block block, Location location) {
    	Block door = gameMapTo.getBlockAt(location, null);
        if (door == null) {
            Direction facing = block.getLocation().getMap().getFacing();
            block.destroy();
            block.setLocation(location.clone());

            if (!kicking) {
                if (gameMapTo.getFacing() == Direction.RIGHT) {
                    //se mete
                    musicaAumento.playOnce();

                    block.push(Direction.LEFT);
                } else if (gameMapTo.getFacing() == Direction.LEFT) {
                    musicaAumento.playOnce();

                    block.push(Direction.RIGHT);
                } else if (gameMapTo.getFacing() == Direction.UP) {
                    musicaAumento.playOnce();

                    block.push(Direction.DOWN);
                } else if (gameMapTo.getFacing() == Direction.DOWN) {
                    musicaAumento.playOnce();

                    block.push(Direction.UP);
                }
            } else {
                if (facing == Direction.RIGHT) {
                    musicaDecrecer.playOnce();

                    block.push(Direction.RIGHT);
                } else if (facing == Direction.LEFT) {
                    musicaDecrecer.playOnce();

                    block.push(Direction.LEFT);
                } else if (facing == Direction.UP) {
                    musicaDecrecer.playOnce();

                    block.push(Direction.UP);
                } else if (facing == Direction.DOWN) {
                    musicaDecrecer.playOnce();

                    block.push(Direction.DOWN);
                }
            }

            location.getMap().getBlocks().add(block);
            return true;
        }
        return false;
    }

    /**
     * Obtiene la ubicación final del juego.
     *
     * @return La ubicación final del juego.
     */
    public Location getEndLocation() {
    	for (GameMap gameMap : maps) {
            if (gameMap.getEnd() != null) {
                System.out.println("End location: " + gameMap.getEnd());
                return gameMap.getEnd();
            }
        }
        return null;
    }
    /**
     * Comprueba si el jugador ha ganado el juego.
     */
    public void checkWin() {
        boolean win = true;

        for (GameMap gameMap : maps) {
            for (Location location : gameMap.getEndCages()) {
                if (gameMap.getBlockAt(location, null) == null) {
                    win = false;
                    break;
                }
            }
        }

        if (player.getLocation().getMap() != getEndLocation().getMap() || player.getLocation().getX() != getEndLocation().getX() || player.getLocation().getY() != getEndLocation().getY()) {
            win = false;
        }

        if (win) {
            java.time.Duration duration = java.time.Duration.between(start, Instant.now());

            long minutes = duration.toMinutes();
            long seconds = duration.toSecondsPart();

            StatsController.guardar(mapData.getId(), minutes + "m " + seconds + "s", movimientos);

            alertWin();
            MapData nextMapData = App.getGameController().getMapDataController().getMapById(mapData.getId() + 1);
            music.stopMusic();
            if(nextMapData != null) {
                App.getGameController().getMapController().setGame(new Game(nextMapData));
            } else {
                App.getControlador().getView().setCenter(App.getControlador().getCreditosController().getView());
                App.getControlador().getCreditosController().subir();
            }
        }
    }
    

    /**
     * Muestra una alerta de victoria.
     */
    public void alertWin() {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UNDECORATED);

        Image image = new Image("/assets/xdxd.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(200);

        ImageView newImageView = new ImageView(MainController.getUserData().getSkin());
        newImageView.setFitWidth(45);
        newImageView.setFitHeight(45);

        newImageView.setX((imageView.getFitWidth() - newImageView.getFitWidth()) / 2);
        newImageView.setY(40);

        Group group = new Group(imageView, newImageView);
        alert.getDialogPane().setContent(group);


        alert.getDialogPane().setStyle("-fx-background-color: transparent;");

        alert.getDialogPane().setGraphic(group);
        DialogPane dialogPane = alert.getDialogPane();

        dialogPane.setStyle("-fx-background-color: #f48d01;");
        alert.getDialogPane().lookupButton(ButtonType.OK).setOpacity(0);

        alert.setWidth(320);
        alert.setHeight(220);

        alert.setX((Screen.getPrimary().getVisualBounds().getWidth() - alert.getWidth()) / 2);
        alert.setY((Screen.getPrimary().getVisualBounds().getHeight() - alert.getHeight()) / 2);

        Duration duration = Duration.seconds(2);
        KeyFrame keyFrame = new KeyFrame(duration, event -> alert.close());

        Timeline timeline = new Timeline(keyFrame);
        timeline.play();

        alert.showAndWait();
    }

    /**
     * Método de bucle principal del juego.
     *
     * @param now Tiempo actual.
     */
    @Override
    public void handle(long now) {
        player.getLocation().getMap().handleMap();
        player.updatePlayer();
    }

    /**
     * Maneja los eventos de pulsación de teclas.
     *
     * @param code Código de la tecla presionada.
     */
    private void handleKeyPress(KeyCode code) {
    	if(MainController.getUserData().letras()) {
            if (code == KeyCode.W) {
                player.handleMovement(Direction.UP);
            } else if (code == KeyCode.A) {
                player.handleMovement(Direction.LEFT);
            } else if (code == KeyCode.S) {
                player.handleMovement(Direction.DOWN);
            } else if (code == KeyCode.D) {
                player.handleMovement(Direction.RIGHT);
            }
        } else {
            if (code == KeyCode.UP) {
                player.handleMovement(Direction.UP);
            } else if (code == KeyCode.LEFT) {
                player.handleMovement(Direction.LEFT);
            } else if (code == KeyCode.DOWN) {
                player.handleMovement(Direction.DOWN);
            } else if (code == KeyCode.RIGHT) {
                player.handleMovement(Direction.RIGHT);
            }
        }

        if(code == KeyCode.ESCAPE) {
            App.getControlador().getView().setCenter(App.getControlador().getInicioController().getView());
        }

        if (code == KeyCode.R) {
            music.stopMusic();
            music = new Music("Reiniciar");
            music.playOnce();
            App.getGameController().getMapController().setGame(new Game(App.getGameController().getMapDataController().getMapById(mapData.getId())));
        }
    }
}

