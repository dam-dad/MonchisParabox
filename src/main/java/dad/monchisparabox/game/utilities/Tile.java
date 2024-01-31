package dad.monchisparabox.game.utilities;

import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.GameMap;
import dad.monchisparabox.game.entities.StaticBlock;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Tile {

    public static String mapa = """
            ##########
            #........#
            p........#
            #........#
            #..1..1..#
            #........#
            #........#
            #........#
            #........#
            ##########
            @1
            ##########
            #.....N..#
            #....EMO.#
            #b....S..#
            #........#
            #........#
            #........#
            #........#
            #........#
            ##########
            @2
            ##########
            #_....####
            #.......=#
            #....#####
            #p...#####
            #....#####
            #....#####
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

                    switch (caracter) {
                        case 'p':
                            map.setStart(new Location(map, columna, fila));
                            break;
                            
                        case '_':
                            // location terminarCaja
                            map.getEndCages().add(new Location(map, columna, fila));
                            break;
                            
                        case '=':
                            // location endGame
                            map.setEnd(new Location(map, columna, fila));
                            break;
                            
                        case '#':
                            // StaticBlock, limite
                            map.getBlocks().add(new StaticBlock(new Location(map, columna, fila)));
                            break;
                            
                        case 'b':
                            // Una caja de estas
                            
                            break;
                            
                        case '.':
                            // Vacío, nada, debería ser fondo
                            
                            break;
                            
                        case 'M':
                            // Es un mapa, esto no puede ser M, número
                            /*Rectangle M = new Rectangle(50, 50);
                            M.setFill(Color.VIOLET);
                            map.add(M, columna, fila);*/
                            break;
                    }
                }
            }
            mapList.add(map);
        }
        return mapList;
    }
}
