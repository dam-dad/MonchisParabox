package dad.monchisparabox.ui.controller;

import dad.monchisparabox.skin.Skin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.media.AudioClip;

public class MainController implements Initializable {
	
	// controllers
	private inicioController inicioController;
	private tuningController tuningController;	
	private ajustesController ajustescontroller;
	private Skin skin;
	
	//model
	private boolean musica = true, efectos = true;
    private double xOffset, yOffset;
	private int posicionActual = 0;
    private List<String> skins = Arrays.asList("/assets/skins/skin1.png", "/assets/skins/skin2.png", "/assets/skins/skin3.png", "/assets/skins/skin4.png", "/assets/skins/skin5.png");
	
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
		
		String audioFile = getClass().getResource("/music/musica_inicio.mp3").toString();
		AudioClip audioClip = new AudioClip(audioFile);
		audioClip.setVolume(0.3f);
		audioClip.play();

		
		// CONTROLADORES
		inicioController = new inicioController();
		
		skin = new Skin();
		skin.setSkin(new Image(skins.get(posicionActual)));
		skin.setSkinFinal(new Image(skins.get(posicionActual)));
		skin.setComplemento1(new Image("/assets/complementos/c0.png"));
		skin.setComplemento2(new Image("/assets/complementos/c0.png"));
		
		//INICIO > JUGAR
		inicioController.setOnJugar (e -> {
			efectoBoton();
			Image skinFinal = generarSkinFinal(skin.getSkin(), new Image(skin.getComplemento1().getUrl().substring(skin.getComplemento1().getUrl().indexOf("/assets/"), skin.getComplemento1().getUrl().length() - 4) + "F.png"));
			generarSkinFinal(skinFinal, new Image(skin.getComplemento2().getUrl().substring(skin.getComplemento2().getUrl().indexOf("/assets/"), skin.getComplemento2().getUrl().length() - 4) + "F.png"));
			skin.setSkinFinal(skinFinal);
			
			//MUSICA / EFECTOS / IMAGEN
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
			efectoBoton();
			view.setCenter(ajustescontroller.getView());
		});
		
		//INICIO > SKIN SIGUIENTE 
		inicioController.setOnSiguiente(e -> {
			efectoBoton();
	    	posicionActual++;
		    
		    if (posicionActual >= skins.size()) {
		        posicionActual = 0;
		    }
		    skin.setSkin(new Image(skins.get(posicionActual)));
		    inicioController.cambiarSkin(new Image(skins.get(posicionActual)));
		});
		
		//INICIO > SKIN ANTERIOR 
		inicioController.setOnAnterior (e -> {
			efectoBoton();
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
			efectoBoton();
			tuningController.setskinActual(skin.getSkin());
			tuningController.setcomplemento1Actual(skin.getComplemento1());
			tuningController.setcomplemento2Actual(skin.getComplemento2());
			
			view.setCenter(tuningController.getView());
		});
		
		//AJUSTES > INICIO
		ajustescontroller.setOnAtras (e -> {
			efectoBoton();
			view.setCenter(inicioController.getView());
		});
		
		//AJUSTES > SALIR
		ajustescontroller.setOnSalir (e -> {
			efectoBoton();
			Platform.exit();
		});
		
		//AJUSTES > MODIFICAR VOLUMEN
		ajustescontroller.setOnVolumen (e -> {
			if (musica) {
				efectoBoton();
				audioClip.stop();
				audioClip.play(ajustescontroller.getVolumen() / 100);
			}
		});
		
		//AJUSTES > EFECTOS OFF
		ajustescontroller.setonOffEfectos (e -> {
			efectoBoton();
			efectos = false;
		});
		
		//AJUSTES > EFECTOS ON
		ajustescontroller.setonOnEfectos (e -> {
			efectoBoton();
			efectos = true;
		});	
		
		//AJUSTES > MÚSICA OFF 
		ajustescontroller.setonOffMusica (e -> {
			efectoBoton();
			audioClip.stop();
			musica = false;
		});
				
		//AJUSTES > MÚSICA ON
		ajustescontroller.setonOnMusica (e -> {
			efectoBoton();
			audioClip.play();
			musica = true;
		});	
		
		//AJUSTES > FLECHAS ON
		ajustescontroller.setonFlechas (e -> {
			efectoBoton();
			System.out.println("ACTIVAR FLECHAS");
			//ACTIVAR FLECHAS 
			//DESACTIVAR LETRAS
		});	
		
		//AJUSTES > LETRAS ON
		ajustescontroller.setonLetras (e -> {
			efectoBoton();
			System.out.println("ACTIVAR LETRAS");
			//ACTIVAR LETRAS
		    //DESACTIVAR FLECHAS
		});	
		
		//AJUSTES > GENERAR PDF
		ajustescontroller.setOnPdf (e -> {
			efectoBoton();
			System.out.println("GENERAR PDF");
			//GENERAR PDF
		});	
		
		//AJUSTES > EXPANDIR / REDUCIR
		ajustescontroller.setOnExpandir (e -> {
			efectoBoton();
		});	
		
		
		//TUNING > INICIO
		tuningController.setOnAtras (e -> {
			efectoBoton();
			skin.setSkin(tuningController.getskinActual());
			skin.setComplemento1(tuningController.getcomplemento1Actual());
			skin.setComplemento2(tuningController.getcomplemento2Actual());

			inicioController.setskinActual(skin.getSkin());
			inicioController.setcomplemento1Actual(skin.getComplemento1());
			inicioController.setcomplemento2Actual(skin.getComplemento2());
			
			view.setCenter(inicioController.getView());
		});
		
		//TUNING > EFECTOS
		tuningController.setOnSonido (e -> {
			efectoBoton();
		});
		
		//TUNING > SALIR
		tuningController.setOnSalir (e -> {
			efectoBoton();
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
	    File outputFile = new File("src/main/resources/assets/skinFinal.png");

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

        return new Image(outputFile.toURI().toString());
	}

	// Método auxiliar para crear un BufferedImage a partir de un array de enteros
	private BufferedImage createBufferedImage(int[] imageData, int width, int height) {
	    DataBufferInt buffer = new DataBufferInt(imageData, imageData.length);
	    WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, new int[]{0xFF0000, 0x00FF00, 0x0000FF, 0xFF000000}, null);
	    ColorModel colorModel = new DirectColorModel(32, 0xFF0000, 0x00FF00, 0x0000FF, 0xFF000000);
	    return new BufferedImage(colorModel, raster, false, null);
	}
	
	private void efectoBoton() {
		if (efectos) {
			String audioFile = getClass().getResource("/music/efecto_boton.mp3").toString();
			AudioClip audio = new AudioClip(audioFile);
			audio.play();
		}
	}
	
}
