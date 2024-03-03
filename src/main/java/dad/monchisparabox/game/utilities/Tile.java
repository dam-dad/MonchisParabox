package dad.monchisparabox.game.utilities;

import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.GameMap;
import dad.monchisparabox.game.block.Block;
import dad.monchisparabox.game.block.BoxBlock;
import dad.monchisparabox.game.block.LimitBlock;
import dad.monchisparabox.game.block.MapBlock;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Tile {

    public static String mapa = """
            ##########
            #...#....#
            #...b.b..#
            #.....b..#
            #........#
            #...######
            ##.......#
            #...M....#
            #...p.#.b#
            ##########
            @
            ##########
            #.._.....#
            #........#
            #.b......#
            #.....#..p
            #........#
            #.....M..#
            #........#
            #........#
            ##########
            @
            ##########
            #_....####
            #.......=#
            #....#####
            #....#####
            #....#####
            p....#####
            #...b....#
            ##..#....#
            ##########
            """;

    public static List<GameMap> tiles(String mapas) {
        List<GameMap> mapList = new ArrayList<>();

        String[] mapasArray = mapas.trim().split("@");

        for (String mapa : mapasArray) {
            GameMap map = new GameMap(10, 10, 1);

            String[] lineas = mapa.trim().split("\n");

            for (int fila = 0; fila < lineas.length; fila++) {
                for (int columna = 0; columna < lineas[fila].length(); columna++) {
                    char caracter = lineas[fila].charAt(columna);

                    // Este es el fondo vacio
                    Rectangle empty = new Rectangle(50, 50);
                    empty.setFill(Color.ALICEBLUE);
                    map.add(empty, columna, fila);

                    switch (caracter) {
                        case 'p':
                            map.setStart(new Location(map, columna, fila));
                            break;
                        case '_':
                            map.getEndCages().add(new Location(map, columna, fila));
                            break;
                        case '=':
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
            for(Block block : gameMap.getBlocks()) {
                if(block instanceof MapBlock mapBlock) {
                    mapBlock.setGameMap(mapList.get(i+1));
                }
            }
        }

        return mapList;
    }
}
