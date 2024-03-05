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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Game extends AnimationTimer {

    private Player player;
    private ArrayList<GameMap> maps = new ArrayList<>();
    private Music music;

    private MapData mapData;

     private Music musicaAumento = new Music("AumentoCaja");
     private   Music musicaDecrecer = new Music("DecrecerCaja");
    public Game(MapData mapData) {
        this.mapData = mapData;
        maps.addAll(mapData.getGameMaps());

        player = new Player();

        player.setLocation(maps.get(0).getStart());

        music = new Music(mapData.getCancion());

        music.play(MainController.getUserData().getVolumen()/100.0);

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
    }

    public void changeMap(GameMap gameMap, Location location, boolean joining) {
        Block door = gameMap.getBlockAt(location, null);
        if (door == null) {
            if (joining) {
                //entra
              musicaAumento.playOnce();
                gameMap.setJoinLocation(new Location(player.getLocation().getMap(), player.getLocation().getLastX(), player.getLocation().getLastY()));

            } else {

             musicaDecrecer.playOnce();
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
            xd();
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

    public static void xd() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UNDECORATED);

        Image image = new Image("/assets/xdxd.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(200);

        alert.getDialogPane().setStyle("-fx-background-color: transparent;");
        alert.getDialogPane().setGraphic(imageView);
        DialogPane dialogPane = alert.getDialogPane();

        dialogPane.setStyle("-fx-background-color: #003373;");
        alert.getDialogPane().lookupButton(ButtonType.OK).setOpacity(0);

        alert.show();

        alert.setWidth(320);
        alert.setHeight(220);

        alert.setX((Screen.getPrimary().getVisualBounds().getWidth() - alert.getWidth()) / 2);
        alert.setY((Screen.getPrimary().getVisualBounds().getHeight() - alert.getHeight()) / 2);

        Duration duration = Duration.seconds(2);
        KeyFrame keyFrame = new KeyFrame(duration, event -> alert.close());

        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
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

        if (code == KeyCode.R) {
            music.stopMusic();
            music = new Music("Reiniciar");
            music.playOnce();
            App.getGameController().getMapController().setGame(new Game(App.getGameController().getMapDataController().getMapById(mapData.getId())));
        }
    }
}
