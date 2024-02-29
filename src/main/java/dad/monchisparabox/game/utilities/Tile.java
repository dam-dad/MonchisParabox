package dad.monchisparabox.game.utilities;

import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.GameMap;
import dad.monchisparabox.game.block.BoxBlock;
import dad.monchisparabox.game.block.LimitBlock;

import java.util.ArrayList;
import java.util.List;

public class Tile {

    public static String mapa = """
            ##########
            #........#
            p....b...#
            #........#
            #..b..b..#
            #........#
            #...b....#
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
                            map.getEndCages().add(new Location(map, columna, fila));
                            break;
                        case '=':
                            map.setEnd(new Location(map, columna, fila));
                            break;
                        case '#':
                            map.getBlocks().add(new LimitBlock(new Location(map, columna, fila)));
                            break;
                        case 'b':
                            map.getBlocks().add(new BoxBlock(new Location(map, columna, fila)));
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
