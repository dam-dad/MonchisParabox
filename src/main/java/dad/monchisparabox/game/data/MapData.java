package dad.monchisparabox.game.data;

import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.GameMap;
import dad.monchisparabox.game.block.Block;
import dad.monchisparabox.game.block.BoxBlock;
import dad.monchisparabox.game.block.LimitBlock;
import dad.monchisparabox.game.block.MapBlock;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class MapData {
    private int id;
    private String nombre;
    private List<List<String>> mapa;
    private String cancion;

    // Constructor, getters y setters
    public MapData() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<List<String>> getMapa() {
        return mapa;
    }

    public void setMapa(List<List<String>> mapa) {
        this.mapa = mapa;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public List<GameMap> getGameMaps() {
        return loadFromTile(mapa);
    }

    private List<GameMap> loadFromTile(List<List<String>> mapasArray) {
        List<GameMap> mapList = new ArrayList<>();

        for (List<String> lineas : mapasArray) {
            GameMap map = new GameMap(10, 10, 1);

            for (int fila = 0; fila < lineas.size(); fila++) {
                for (int columna = 0; columna < lineas.get(fila).length(); columna++) {
                    char caracter = lineas.get(fila).charAt(columna);

                    int numCols = lineas.get(fila).length();
                    int numRows = lineas.size();

                    boolean isCorner = (fila == 0 && columna == 0) || (fila == 0 && columna == numCols - 1) ||
                            (fila == numRows - 1 && columna == 0) || (fila == numRows - 1 && columna == numCols - 1);

                    if (!isCorner) {
                        Rectangle empty = new Rectangle(50, 50);
                        empty.setFill(Color.DARKGREY);
                        map.add(empty, columna, fila);
                    }

                    switch (caracter) {
                        case 'p':
                            map.setStart(new Location(map, columna, fila));
                            break;
                        case '_':
                            map.add(new ImageView(new Image("/assets/graffiti/caja_position.png")), columna, fila);
                            map.getEndCages().add(new Location(map, columna, fila));
                            break;
                        case '=':
                            map.add(new ImageView(new Image("/assets/graffiti/player_finish.png")), columna, fila);
                            map.setEnd(new Location(map, columna, fila));
                            break;
                        case '#':
                            LimitBlock limitBlock = new LimitBlock(new Location(map, columna, fila));
                            map.getBlocks().add(limitBlock);
                            break;
                        case 'b':
                            map.getBlocks().add(new BoxBlock(new Location(map, columna, fila)));
                            break;
                        case 'M':
                            MapBlock mapBlock = new MapBlock(new Location(map, columna, fila));
                            map.getBlocks().add(mapBlock);
                            break;
                        case '.':
                            //nada
                            break;
                    }
                }
            }

            mapList.add(map);
        }

        for (int i = 0; i < mapList.size(); i++) {
            GameMap gameMap = mapList.get(i);
            int bloq = 1;
            for (Block block : gameMap.getBlocks()) {
                if (block instanceof MapBlock mapBlock) {
                    mapBlock.setGameMap(mapList.get(i + bloq));
                    bloq++;
                    mapBlock.renderMapview();
                }
            }
        }

        return mapList;
    }
}
