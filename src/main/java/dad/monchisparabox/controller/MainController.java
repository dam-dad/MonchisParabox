package dad.monchisparabox.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import dad.proyectoDAD.skin.Skin;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MainController implements Initializable {
	
	// controllers
	private inicioController inicioController;
	private tuningController tuningController;	
	private ajustesController ajustescontroller;
	private Skin skin;
	
	//model
    private double xOffset;
    private double yOffset;
	private int posicionActual = 0;
    private List<String> skins = Arrays.asList("/images/skins/skin1.png", "/images/skins/skin2.png", "/images/skins/skin3.png", "/images/skins/skin4.png", "/images/skins/skin5.png");
	
	// view
	@FXML
	private BorderPane view;

	public MainController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// CONTROLADORES
		inicioController = new inicioController();
		
		skin = new Skin();
		skin.setSkin(new Image(skins.get(posicionActual)));
		skin.setSkinFinal(new Image(skins.get(posicionActual)));
		skin.setComplemento1(new Image("/images/complementos/c0.png"));
		skin.setComplemento2(new Image("/images/complementos/c0.png"));
		
		//INICIO > JUGAR
		inicioController.setOnJugar (e -> {
			Image skinFinal = generarSkinFinal(skin.getSkin(), new Image(skin.getComplemento1().getUrl().substring(skin.getComplemento1().getUrl().indexOf("/images/"), skin.getComplemento1().getUrl().length() - 4) + "F.png"));
			generarSkinFinal(skinFinal, new Image(skin.getComplemento2().getUrl().substring(skin.getComplemento2().getUrl().indexOf("/images/"), skin.getComplemento2().getUrl().length() - 4) + "F.png"));
			skin.setSkinFinal(skinFinal);
		});	
		
		//INICIO > MOVER
		inicioController.setOnMover (e -> {
			Stage stage = (Stage) view.getScene().getWindow();
			double newX = e.getScreenX() - xOffset;
            double newY = e.getScreenY() - yOffset;
            stage.setX(newX);
            stage.setY(newY);
		});

		//INICIO > MOVER PRESSED
		inicioController.setOnMoverPressed (e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
		});
		
		//INICIO > AJUSTES
        ajustescontroller = new ajustesController();
		inicioController.setOnAjustes (e -> {
			view.setCenter(ajustescontroller.getView());
			System.out.print("Ajustes");
		});
		
		//INICIO > SKIN SIGUIENTE 
		inicioController.setOnSiguiente(e -> {
	    	posicionActual++;
		    
		    if (posicionActual >= skins.size()) {
		        posicionActual = 0;
		    }
		    skin.setSkin(new Image(skins.get(posicionActual)));
		    inicioController.cambiarSkin(new Image(skins.get(posicionActual)));
		});
		
		//INICIO > SKIN ANTERIOR 
		inicioController.setOnAnterior (e -> {
		    posicionActual--;
		    
		    if (posicionActual < 0) {
		        posicionActual = skins.size() - 1;
		    }

		    skin.setSkin(new Image(skins.get(posicionActual)));
		    inicioController.cambiarSkin(new Image(skins.get(posicionActual)));
		});
		
		//INICIO > TUNING
		tuningController = new tuningController();
		inicioController.setOnEditar (e -> {
			tuningController.setskinActual(skin.getSkin());
			tuningController.setcomplemento1Actual(skin.getComplemento1());
			tuningController.setcomplemento2Actual(skin.getComplemento2());
			
			view.setCenter(tuningController.getView());
		});
		
		//AJUSTES > INICIO
		ajustescontroller.setOnAtras (e -> {
			view.setCenter(inicioController.getView());
		});
		
		//AJUSTES > SALIR
		ajustescontroller.setOnSalir (e -> {
			Platform.exit();
		});
		
		//AJUSTES > EFECTIS OFF
		ajustescontroller.setonOffEfectos (e -> {
			System.out.println("QUITAR EFECTOS DE SONIDO");
		});
		
		//AJUSTES > EFECTOS ON
		ajustescontroller.setonOnEfectos (e -> {
			System.out.println("PONER EFECTOS DE SONIDO");
		});	
		
		//AJUSTES > MÚSICA OFF 
		ajustescontroller.setonOffMusica (e -> {
			System.out.println("QUITAR MÚSICA");
		});
				
		//AJUSTES > MÚSICA ON
		ajustescontroller.setonOnMusica (e -> {
			System.out.println("PONER MÚSICA");
		});	
		
		//AJUSTES > FLECHAS ON
		ajustescontroller.setonFlechas (e -> {
			System.out.println("ACTIVAR FLECHAS");
			//ACTIVAR FLECHAS 
			//DESACTIVAR LETRAS
		});	
		
		//AJUSTES > LETRAS ON
		ajustescontroller.setonLetras (e -> {
			System.out.println("ACTIVAR LETRAS");
			//ACTIVAR LETRAS
		    //DESACTIVAR FLECHAS
		});	
		
		
		//TUNING > INICIO
		tuningController.setOnAtras (e -> {
			skin.setSkin(tuningController.getskinActual());
			skin.setComplemento1(tuningController.getcomplemento1Actual());
			skin.setComplemento2(tuningController.getcomplemento2Actual());

			inicioController.setskinActual(skin.getSkin());
			inicioController.setcomplemento1Actual(skin.getComplemento1());
			inicioController.setcomplemento2Actual(skin.getComplemento2());
			
			view.setCenter(inicioController.getView());
		});
		
		//TUNING > SALIR
		tuningController.setOnSalir (e -> {
			Platform.exit();
		});
		
		//MOSTRAMOS LA VISTA DEL INICIO
		view.setCenter(inicioController.getView());
	}
	
	public BorderPane getView() {
		return view;
	}
	
	public Image generarSkinFinal(Image skin, Image complemento1) {
	    // Crea un ImageView para cada imagen
	    ImageView skinView = new ImageView(skin);
	    ImageView complemento1View = new ImageView(complemento1);

	    // Crea un Pane para contener las imágenes
	    Pane pane = new Pane(skinView, complemento1View);

	    // Establece el tamaño del Pane
	    pane.setPrefSize(600, 600);

	    // Superpone el complemento1 sobre la piel
	    complemento1View.setLayoutX(0);
	    complemento1View.setLayoutY(0);

	    // Crea una nueva imagen para guardar el resultado
	    WritableImage resultImage = new WritableImage(600, 600);

	    // Renderiza y guarda la imagen resultante en el objeto WritableImage
	    pane.snapshot(null, resultImage);

	    // Guarda la imagen en un archivo (cambia la ruta y el nombre del archivo según tu preferencia)
	    File outputFile = new File("src/main/resources/images/skinFinal.png");

	    // Utiliza un PixelReader para obtener los píxeles de la imagen de JavaFX
	    PixelReader pixelReader = resultImage.getPixelReader();
	    int width = (int) resultImage.getWidth();
	    int height = (int) resultImage.getHeight();

	    // Crea un array de bytes para almacenar los datos de la imagen
	    int[] imageData = new int[width * height];

	    // Lee los píxeles de la imagen y almacénalos en el array de bytes
	    pixelReader.getPixels(0, 0, width, height, javafx.scene.image.PixelFormat.getIntArgbInstance(), imageData, 0, width);

	    // Guarda los datos en un archivo usando la clase Files o cualquier otra forma que prefieras
	    // Aquí se utiliza ImageIO solo para escribir el array de bytes en un archivo PNG
	    try {
	        ImageIO.write(createBufferedImage(imageData, width, height), "png", outputFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    Image skinFinal = new Image(outputFile.toURI().toString());
	    
	    return skinFinal;
	}

	// Método auxiliar para crear un BufferedImage a partir de un array de enteros
	private BufferedImage createBufferedImage(int[] imageData, int width, int height) {
	    DataBufferInt buffer = new DataBufferInt(imageData, imageData.length);
	    WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, new int[]{0xFF0000, 0x00FF00, 0x0000FF, 0xFF000000}, null);
	    ColorModel colorModel = new DirectColorModel(32, 0xFF0000, 0x00FF00, 0x0000FF, 0xFF000000);
	    return new BufferedImage(colorModel, raster, false, null);
	}
	
	
}
