package dad.monchisparabox.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Controlador para la vista de ajustes en el juego Monchi's Parabox.
 */
public class ajustesController implements Initializable {

    // Acciones
    private EventHandler<MouseEvent> onAtras;
    private EventHandler<MouseEvent> onSalir;
    private EventHandler<MouseEvent> onOffEfectos;
    private EventHandler<MouseEvent> onOnEfectos;
    private EventHandler<MouseEvent> onOffMusica;
    private EventHandler<MouseEvent> onOnMusica;
    private EventHandler<MouseEvent> onLetras;
    private EventHandler<MouseEvent> onFlechas;
    private EventHandler<MouseEvent> onVolumen;
    private EventHandler<MouseEvent> onPdf;
    private EventHandler<MouseEvent> OnExpandir;

    // Modelo
    private double volumen;

    // Vista
    @FXML
    private ImageView expandirReducirImageView;

    @FXML
    private ImageView VolumenMenosImageView;

    @FXML
    private ImageView atrasImageView;

    @FXML
    private ImageView diplomaImageView;

    @FXML
    private Label diplomaLabel;

    @FXML
    private ImageView efectosImageView;

    @FXML
    private ImageView flechasImageView;

    @FXML
    private ImageView letrasImageView;

    @FXML
    private ImageView musicaImageView;

    @FXML
    private ImageView salirImageView;

    @FXML
    private ImageView volumenMasImageView;

    @FXML
    private Slider volumenSlider;

    @FXML
    private BorderPane view;

    /**
     * Obtiene la vista del controlador.
     *
     * @return La vista del controlador.
     */
    public BorderPane getView() {
        return view;
    }

    /**
     * Constructor del controlador de ajustes.
     */
    public ajustesController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AjustesView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        volumenSlider.setValue(30);
        setVolumen(30);

        volumenSlider.valueProperty().addListener((o, ov, nv) -> {
            double volumen = volumenSlider.valueProperty().doubleValue();
            setVolumen(volumen);

            if (onVolumen != null) onVolumen.handle(null);

            if (volumen == 0) {
                VolumenMenosImageView.setImage(new Image("/assets/volumen0.png"));
            } else {
                VolumenMenosImageView.setImage(new Image("/assets/volumen1.png"));
            }
        });
    }

    /**
     * Obtiene el valor del volumen.
     *
     * @return El valor del volumen.
     */
    public double getVolumen() {
        return volumen;
    }

    /**
     * Establece el valor del volumen.
     *
     * @param volumen El valor del volumen a establecer.
     */
    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }
	
    
    /**
     * Establece el manejador para el evento de cambiar el volumen.
     *
     * @param onVolumen El manejador del evento de cambiar el volumen.
     */
	public void setOnVolumen(EventHandler<MouseEvent> onVolumen) {
		this.onVolumen = onVolumen;
	}
	
	/**
     * Método manejador para el evento de hacer clic en el botón de expandir/reducir.
     *
     * @param event El evento de clic del ratón.
     */
	@FXML
	void onExpandirReducirClicked(MouseEvent event) {

		if(OnExpandir != null) OnExpandir.handle(event);
		
		String nombreImagen = expandirReducirImageView.getImage().getUrl().substring(expandirReducirImageView.getImage().getUrl().lastIndexOf("/") + 1);
		Stage stage = (Stage) view.getScene().getWindow();
		
		if (nombreImagen.equals("expandir.png")) {
			expandirReducirImageView.setImage(new Image("/assets/reducir.png"));
	        stage.setMaximized(true);
		    
		}else if (nombreImagen.equals("reducir.png")) {
			expandirReducirImageView.setImage(new Image("/assets/expandir.png"));
			stage.setMaximized(false);
			stage.setHeight(522);
			stage.setWidth(928);
		}
	}
	
	/**
     * Establece el manejador para el evento de expandir/reducir.
     *
     * @param OnExpandir El manejador del evento de expandir/reducir.
     */
	public void setOnExpandir(EventHandler<MouseEvent> OnExpandir) {
		this.OnExpandir = OnExpandir;
	}
	
	/**
     * Método manejador para el evento de hacer clic en el botón de retroceso.
     *
     * @param event El evento de clic del ratón.
     */
	@FXML
	void OnAtrasClicked(MouseEvent event) {
		if(onAtras != null) onAtras.handle(event);
	}
	
	/**
     * Establece el manejador para el evento de retroceder.
     *
     * @param onAtras El manejador del evento de retroceder.
     */
	public void setOnAtras(EventHandler<MouseEvent> onAtras) {
		this.onAtras = onAtras;
	}
		
	/**
	 * Método manejador para el evento de hacer clic en el botón de salida.
	 *
	 * @param event El evento de clic del ratón.
	 */
	@FXML
	void onSalirClicked(MouseEvent event) {
	    if(onSalir != null) onSalir.handle(event);
	}

	/**
	 * Establece el manejador para el evento de salida.
	 *
	 * @param onSalir El manejador del evento de salida.
	 */
	public void setOnSalir(EventHandler<MouseEvent> onSalir) {
	    this.onSalir = onSalir;
	}

	/**
	 * Método manejador para el evento de hacer clic en el botón de diploma.
	 *
	 * @param event El evento de clic del ratón.
	 */
	@FXML
	void onDiplomaClicked(MouseEvent event) {
	    if(onPdf != null) onPdf.handle(event);
	}

	/**
	 * Método manejador para el evento de hacer clic en la etiqueta de diploma.
	 *
	 * @param event El evento de clic del ratón.
	 */
	@FXML
	void onDiplomaLabelClicked(MouseEvent event) {
	    if(onPdf != null) onPdf.handle(event);
	}

	/**
	 * Establece el manejador para el evento de diploma.
	 *
	 * @param onPdf El manejador del evento de diploma.
	 */
	public void setOnPdf(EventHandler<MouseEvent> onPdf) {
	    this.onPdf = onPdf;
	}

	/**
	 * Método manejador para el evento de hacer clic en el botón de efectos.
	 *
	 * @param event El evento de clic del ratón.
	 */
	@FXML
	void onEfectosClicked(MouseEvent event) {
	    String nombreImagen = efectosImageView.getImage().getUrl().substring(efectosImageView.getImage().getUrl().lastIndexOf("/") + 1);

	    if (nombreImagen.equals("volumen3.png")) {
	        efectosImageView.setImage(new Image("/assets/volumen4.png"));
	        if(onOffEfectos != null) onOffEfectos.handle(event);

	    } else if (nombreImagen.equals("volumen4.png")) {
	        efectosImageView.setImage(new Image("/assets/volumen3.png"));
	        if(onOnEfectos != null) onOnEfectos.handle(event);
	    }
	}

	/**
	 * Establece el manejador para el evento de activar/desactivar efectos.
	 *
	 * @param onOffEfectos El manejador del evento de activar/desactivar efectos.
	 */
	public void setonOffEfectos(EventHandler<MouseEvent> onOffEfectos) {
	    this.onOffEfectos = onOffEfectos;
	}

	/**
	 * Establece el manejador para el evento de encender/apagar efectos.
	 *
	 * @param onOnEfectos El manejador del evento de encender/apagar efectos.
	 */
	public void setonOnEfectos(EventHandler<MouseEvent> onOnEfectos) {
	    this.onOnEfectos = onOnEfectos;
	}

	/**
	 * Método manejador para el evento de hacer clic en el botón de música.
	 *
	 * @param event El evento de clic del ratón.
	 */
	@FXML
	void onMusicaClicked(MouseEvent event) {
	    String nombreImagen = musicaImageView.getImage().getUrl().substring(musicaImageView.getImage().getUrl().lastIndexOf("/") + 1);

	    if (nombreImagen.equals("volumen3.png")) {
	        Image nuevaImagen = new Image("/assets/volumen4.png");
	        musicaImageView.setImage(nuevaImagen);
	        if(onOffMusica != null) onOffMusica.handle(event);

	    } else if (nombreImagen.equals("volumen4.png")) {
	        Image nuevaImagen = new Image("/assets/volumen3.png");
	        musicaImageView.setImage(nuevaImagen);
	        if(onOnMusica != null) onOnMusica.handle(event);
	    }
	}

	/**
	 * Establece el manejador para el evento de activar/desactivar música.
	 *
	 * @param onOffMusica El manejador del evento de activar/desactivar música.
	 */
	public void setonOffMusica(EventHandler<MouseEvent> onOffMusica) {
	    this.onOffMusica = onOffMusica;
	}

	/**
	 * Establece el manejador para el evento de encender/apagar música.
	 *
	 * @param onOnMusica El manejador del evento de encender/apagar música.
	 */
	public void setonOnMusica(EventHandler<MouseEvent> onOnMusica) {
	    this.onOnMusica = onOnMusica;
	}

	/**
	 * Método manejador para el evento de hacer clic en el botón de flechas.
	 *
	 * @param event El evento de clic del ratón.
	 */
	@FXML
	void onFlechasClicked(MouseEvent event) {
	    String nombreImagen = flechasImageView.getImage().getUrl().substring(flechasImageView.getImage().getUrl().lastIndexOf("/") + 1);

	    if (nombreImagen.equals("teclasFlechas.png")) {
	        flechasImageView.setImage(new Image("/assets/teclasFlechasX.png"));
	        letrasImageView.setImage(new Image("/assets/teclasLetras.png"));

	        if(onFlechas != null) onFlechas.handle(event);
	    }
	}

	/**
	 * Establece el manejador para el evento de usar las flechas.
	 *
	 * @param onFlechas El manejador del evento de usar las flechas.
	 */
	public void setonFlechas(EventHandler<MouseEvent> onFlechas) {
	    this.onFlechas = onFlechas;
	}

	/**
	 * Método manejador para el evento de hacer clic en el botón de letras.
	 *
	 * @param event El evento de clic del ratón.
	 */
	@FXML
	void onLetrasClicked(MouseEvent event) {
	    String nombreImagen = letrasImageView.getImage().getUrl().substring(letrasImageView.getImage().getUrl().lastIndexOf("/") + 1);

	    if (nombreImagen.equals("teclasLetras.png")) {
	        letrasImageView.setImage(new Image("/assets/teclasLetrasX.png"));
	        flechasImageView.setImage(new Image("/assets/teclasFlechas.png"));

	        if(onLetras != null) onLetras.handle(event);
	    }
	}

	/**
	 * Establece el manejador para el evento de usar letras.
	 *
	 * @param onLetras El manejador del evento de usar letras.
	 */
	public void setonLetras(EventHandler<MouseEvent> onLetras) {
	    this.onLetras = onLetras;
	}

	/**
	 * Método manejador para el evento de hacer clic en el botón de aumentar volumen.
	 *
	 * @param event El evento de clic del ratón.
	 */
	@FXML
	void onVolumenMasClicked(MouseEvent event) {
	    double nuevoValor = volumenSlider.getValue() + 15;

	    VolumenMenosImageView.setImage(new Image("/assets/volumen1.png"));

	    setVolumen(nuevoValor);
	    volumenSlider.setValue(nuevoValor);
	    if(onVolumen != null) onVolumen.handle(event);
	}

	/**
	 * Método manejador para el evento de hacer clic en el botón de reducir volumen.
	 *
	 * @param event El evento de clic del ratón.
	 */
	@FXML
	void onVolumenMenosClicked(MouseEvent event) {
	    double nuevoValor = volumenSlider.getValue() - 15;

	    if (nuevoValor < 0){
	        nuevoValor = 0;
	    }

	    if (nuevoValor == 0) {
	        VolumenMenosImageView.setImage(new Image("/assets/volumen0.png"));
	    } else {
	        VolumenMenosImageView.setImage(new Image("/assets/volumen1.png"));
	    }

	    setVolumen(nuevoValor);
	    volumenSlider.setValue(nuevoValor);
	    if(onVolumen != null) onVolumen.handle(event);

	}
}
