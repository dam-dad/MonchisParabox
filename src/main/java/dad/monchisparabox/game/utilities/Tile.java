package dad.monchisparabox.game.utilities;

import dad.monchisparabox.game.GameMap;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile {

	public static String mapa0 = """
			##########
			#........#
			b........#
			#........#
			#........#
			#........#
			#........#
			#........#
			#........#
			##########
			""";

	public static String mapa1 = """
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
			""";

	public static String mapa2 = """
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

	public static Rectangle player;

	public static GameMap tile(String mapa) {

		// todo Esto se debería calcular en base a las medidas del string
		GameMap map = new GameMap(10, 10, 1);

		String[] lineas = mapa.trim().split("\n");

		for (int fila = 0; fila < lineas.length; fila++) {
			for (int columna = 0; columna < lineas[fila].length(); columna++) {
				char caracter = lineas[fila].charAt(columna);

				switch (caracter) {

				case 'p':

					// posición inicial del jugador

					player = new Rectangle(50, 50);
					player.setFill(Color.PINK);

					map.add(player, columna, fila);
					System.out.println("jugadorr registrado");
					break;
					
				case '_':

					// location terminarCaja

					Rectangle finBox = new Rectangle(50, 50);
					finBox.setFill(Color.GREEN);
					map.add(finBox, columna, fila);
					break;

				case '=':

					// location endGame

					Rectangle win = new Rectangle(50, 50);
					win.setFill(Color.GREEN);
					map.add(win, columna, fila);
					break;

				case '#':
					
					// StaticBlock, limite
					
					Rectangle obstaculo = new Rectangle(50, 50);
					obstaculo.setFill(Color.BLACK);
					map.add(obstaculo, columna, fila);
					break;

				case 'b':

					// Una caja de estas

					Rectangle box = new Rectangle(50, 50);
					box.setFill(Color.YELLOW);
					map.add(box, columna, fila);
					break;

				case '.':

					// Vacio nada, debería ser fondo

					Rectangle Void = new Rectangle(50, 50);
					Void.setFill(Color.TRANSPARENT);
					map.add(Void, columna, fila);
					break;

				case 'M':

					// Es un mapa, esto no puede ser M, número

					Rectangle M = new Rectangle(50, 50);
					M.setFill(Color.VIOLET);
					map.add(M, columna, fila);
					break;
				}

			}
		}
		return map;

	}
}
