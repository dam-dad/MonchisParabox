package dad.auraengine;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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

	
	
	
	public	static String mapa1= """
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
	
	
	public	static String mapa2= """
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
	public static Map tile(String mapa) {

		
		
		
		Map map = new Map(10, 10, 1);
		
		
		String[] lineas = mapa.trim().split("\n");

		for (int fila = 0; fila < lineas.length; fila++) {
			for (int columna = 0; columna < lineas[fila].length(); columna++) {
				char caracter = lineas[fila].charAt(columna);

				switch (caracter) {

				case '#':
					Rectangle obstaculo = new Rectangle(50, 50);
					obstaculo.setFill(Color.BLACK);
						map.add(obstaculo, columna, fila);
					
					break;

				case 'p':
					
					 player = new Rectangle(50, 50);
				Image player2 = new Image("/assets/blob (1).jpg");
				player.setFill(new ImagePattern(player2));
					map.add(player, columna, fila);
					System.out.println("jugadorr registrado");
					
					break;

				case 'b':

					Rectangle box = new Rectangle(50, 50);
					box.setFill(Color.YELLOW);
						map.add(box, columna, fila);

					

					break;

				case '.':
					Rectangle Void = new Rectangle(50, 50);
					Void.setFill(Color.TRANSPARENT);
						map.add(Void, columna, fila);

				
					break;
				
				
				case 'N':
					Rectangle N = new Rectangle(50, 50);
					N.setFill(Color.PINK);
						map.add(N, columna, fila);

					
				
				
					break;
				case 'S':
					Rectangle S = new Rectangle(50, 50);
					S.setFill(Color.PINK);
						map.add(S, columna, fila);

				
					break;

				case 'E':
					Rectangle E = new Rectangle(50, 50);
					E.setFill(Color.PINK);
					
						map.add(E, columna, fila);

					
					break;

				case 'O':
					Rectangle O = new Rectangle(50, 50);
					O.setFill(Color.PINK);
						map.add(O, columna, fila);

					break;

				case 'M':
					Rectangle M = new Rectangle(50, 50);
					M.setFill(Color.VIOLET);
						map.add(M, columna, fila);

					break;

				case '_':
					Rectangle finBox = new Rectangle(50, 50);
					finBox.setFill(Color.GREEN);
					
						map.add(finBox, columna, fila);

				
					break;
					
				case  '=':
					Rectangle win = new Rectangle(50, 50);			
					Image mapaInt = new Image("/assets/Captura de pantalla 2024-01-21 212213.png");

					win.setFill(new ImagePattern(mapaInt));
						map.add(win, columna, fila);
						

					
				
					break;
			

				}

			}
		}			
		return map;

	}
}
