package dad.monchisparabox.game;

import dad.auraengine.entities.movements.Direction;
import dad.auraengine.entities.movements.Location;
import dad.auraengine.media.Music;
import dad.monchisparabox.App;
import dad.monchisparabox.game.block.Block;
import dad.monchisparabox.game.data.MapData;
import dad.monchisparabox.game.entities.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class Game extends AnimationTimer {

    private Player player;
    private ArrayList<GameMap> maps = new ArrayList<>();
    private Music music;

    private MapData mapData;

    public Game(MapData mapData) {
        this.mapData = mapData;
        maps.addAll(mapData.getGameMaps());

        player = new Player();
        player.setLocation(maps.get(0).getStart());

        App.getMainController().getView().setOnKeyPressed(event -> handleKeyPress(event.getCode()));

        Platform.runLater(App.getMainController().getView()::requestFocus);

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
            App.getMainController().getMapController().getView().setCenter(gameMap);
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulations");
            alert.setHeaderText("You win!");
            alert.setContentText("You have completed the game!");
            alert.showAndWait();

            MapData nextMapData = App.getMainController().getMapDataController().getMapById(mapData.getId() + 1);
            if(nextMapData != null) {
                App.getMainController().getMapController().setGame(new Game(nextMapData));
            } else {
                Alert completed = new Alert(Alert.AlertType.INFORMATION);
                completed.setTitle("Te pasaste el juego mi rey");
                completed.setHeaderText("¡Te lo pasaste!");
                completed.setContentText("¡Te lo pasaste otra vez!");
                completed.showAndWait();
                //TODO Volver a la sala
            }
        }
    }

    // game loop
    @Override
    public void handle(long now) {
        player.getLocation().getMap().handleMap();
        player.updatePlayer();
    }

    private void handleKeyPress(KeyCode code) {
        if (code == KeyCode.W) {
            player.handleMovement(Direction.UP);
        } else if (code == KeyCode.A) {
            player.handleMovement(Direction.LEFT);
        } else if (code == KeyCode.S) {
            player.handleMovement(Direction.DOWN);
        } else if (code == KeyCode.D) {
            player.handleMovement(Direction.RIGHT);
        }

        if (code == KeyCode.R) {
            music = new Music("Reiniciar");
            music.playOnce();
            App.getMainController().getMapController().setGame(new Game(App.getMainController().getMapDataController().getMapById(mapData.getId())));
        }
    }
}
