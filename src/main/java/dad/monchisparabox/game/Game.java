package dad.monchisparabox.game;

import dad.auraengine.entities.movements.Direction;
import dad.auraengine.entities.movements.Location;
import dad.auraengine.media.Music;
import dad.monchisparabox.App;
import dad.monchisparabox.game.block.Block;
import dad.monchisparabox.game.data.MapData;
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

public class Game extends AnimationTimer {

    private Player player;
    private ArrayList<GameMap> maps = new ArrayList<>();
    private Music music;

    private MapData mapData;

    private Instant start;
    private int movimientos;

    public Game(MapData mapData) {
        this.mapData = mapData;
        maps.addAll(mapData.getGameMaps());

        player = new Player();
        player.setLocation(maps.get(0).getStart());

        App.getGameController().getView().setOnKeyPressed(event -> handleKeyPress(event.getCode()));

        Platform.runLater(App.getGameController().getView()::requestFocus);

        init();
    }

    public Player getPlayer() {
        return player;
    }

    public GameMap getInitialMap() {
        return maps.get(0);
    }

    public void init() {
        getInitialMap().load();
        player.render();
        start = Instant.now();
    }

    public void changeMap(GameMap gameMap, Location location, boolean joining) {
        Block door = gameMap.getBlockAt(location, null);
        if (door == null) {
            if (joining) {
                //entra
                music = new Music("AumentoCaja");
                music.playOnce();
                gameMap.setJoinLocation(new Location(player.getLocation().getMap(), player.getLocation().getLastX(), player.getLocation().getLastY()));

            } else {

                music = new Music("DecrecerCaja");
                music.playOnce();
            }

            player.setLocation(location.clone());
            player.getLocation().setLastX(location.getX());
            player.getLocation().setLastY(location.getY());
            player.render();
            gameMap.load();
            App.getGameController().getMapController().getView().setCenter(gameMap);
        }
    }

    public boolean teleportBlockToMap(GameMap gameMapTo, boolean kicking, Block block, Location location) {
        Block door = gameMapTo.getBlockAt(location, null);
        if (door == null) {
            Direction facing = block.getLocation().getMap().getFacing();
            block.destroy();
            block.setLocation(location.clone());

            if (!kicking) {
                if (gameMapTo.getFacing() == Direction.RIGHT) {
                    //se mete
                    music = new Music("AumentoCaja");
                    music.playOnce();
                    block.push(Direction.LEFT);
                } else if (gameMapTo.getFacing() == Direction.LEFT) {
                    music = new Music("AumentoCaja");
                    music.playOnce();
                    block.push(Direction.RIGHT);
                } else if (gameMapTo.getFacing() == Direction.UP) {
                    music = new Music("AumentoCaja");
                    music.playOnce();
                    block.push(Direction.DOWN);
                } else if (gameMapTo.getFacing() == Direction.DOWN) {
                    music = new Music("AumentoCaja");
                    music.playOnce();
                    block.push(Direction.UP);
                }
            } else {
                if (facing == Direction.RIGHT) {
                    music = new Music("DecrecerCaja");
                    music.playOnce();
                    block.push(Direction.RIGHT);
                } else if (facing == Direction.LEFT) {
                    music = new Music("DecrecerCaja");
                    music.playOnce();
                    block.push(Direction.LEFT);
                } else if (facing == Direction.UP) {
                    music = new Music("DecrecerCaja");
                    music.playOnce();
                    block.push(Direction.UP);
                } else if (facing == Direction.DOWN) {
                    music = new Music("DecrecerCaja");
                    music.playOnce();
                    block.push(Direction.DOWN);
                }
            }

            location.getMap().getBlocks().add(block);
            return true;
        }
        return false;
    }

    public Location getEndLocation() {
        for (GameMap gameMap : maps) {
            if (gameMap.getEnd() != null) {
                System.out.println("End location: " + gameMap.getEnd());
                return gameMap.getEnd();
            }
        }
        return null;
    }

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
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();
            long seconds = duration.toSecondsPart();

            // Display the result
            System.out.println("Ha tardado " + hours + ":" + minutes + ":" + seconds);

            alertWin();
            MapData nextMapData = App.getGameController().getMapDataController().getMapById(mapData.getId() + 1);
            if(nextMapData != null) {
                App.getGameController().getMapController().setGame(new Game(nextMapData));
            } else {
                App.getControlador().getView().setCenter(App.getControlador().getCreditosController().getView());
                App.getControlador().getCreditosController().subir();
            }
        }
    }

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

    // game loop
    @Override
    public void handle(long now) {
        player.getLocation().getMap().handleMap();
        player.updatePlayer();
    }

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
            music = new Music("Reiniciar");
            music.playOnce();
            App.getGameController().getMapController().setGame(new Game(App.getGameController().getMapDataController().getMapById(mapData.getId())));
        }
    }
}
