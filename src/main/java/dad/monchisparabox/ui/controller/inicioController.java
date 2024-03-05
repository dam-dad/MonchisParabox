package dad.monchisparabox.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * Controlador para la vista de inicio del juego.
 */
public class inicioController implements Initializable {
	
	// Acciones
	private EventHandler<MouseEvent> onAjustes;
	private EventHandler<MouseEvent> onTuning;
	private EventHandler<MouseEvent> onJugar;
	private EventHandler<MouseEvent> onSiguienteSkin;
	private EventHandler<MouseEvent> onAnteriorSkin;
	private EventHandler<MouseEvent> onMover;
	private EventHandler<MouseEvent> onMoverPressed;
			
	// Vista
	private Image skinActual;
	private Image complemento1Actual;
	private Image complemento2Actual;
	
    @FXML
    private ImageView jugarImageView;
    
    @FXML
    private ImageView skinImageView;

    @FXML
    private ImageView anteriorImageView;

    @FXML
    private ImageView editarImageView;

    @FXML
    private ImageView moverImageView;

    @FXML
    private ImageView siguienteImageView;
    
    @FXML
    private ImageView ajustesImageView;

    @FXML
    private ImageView complemento1ImageView;

    @FXML
    private ImageView complemento2ImageView;

	@FXML
	private BorderPane view;	
	
	/**
	 * Devuelve el contenedor de la vista.
	 * 
	 * @return El contenedor de la vista.
	 */
	public BorderPane getView() {
		return view;
	}
	
	/**
	 * Constructor por defecto.
	 * Carga la vista asociada al controlador.
	 */
	public inicioController() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InicioView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	/**
	 * Método manejador para el evento de hacer clic en el botón de ajustes.
	 * 
	 * @param event El evento de clic del ratón.
	 */
	@FXML
	void onAjustesClicked(MouseEvent event) {
		if(onAjustes != null) onAjustes.handle(event);
	}
	
	/**
	 * Establece el manejador para el evento de ajustes.
	 * 
	 * @param onAjustes El manejador del evento de ajustes.
	 */
	public void setOnAjustes(EventHandler<MouseEvent> onAjustes) {
		this.onAjustes = onAjustes;
	}

	/**
	 * Método manejador para el evento de hacer clic en el botón de anterior.
	 * 
	 * @param event El evento de clic del ratón.
	 */
    @FXML
    void onAnteriorClicked(MouseEvent event) {
	    if(onAnteriorSkin != null) onAnteriorSkin.handle(event);
	}
	
	/**
	 * Establece el manejador para el evento de clic en el botón de anterior.
	 * 
	 * @param onAnteriorSkin El manejador del evento de clic en el botón de anterior.
	 */
	public void setOnAnterior(EventHandler<MouseEvent> onAnteriorSkin) {
		this.onAnteriorSkin = onAnteriorSkin;
	}
    
	/**
	 * Método manejador para el evento de hacer clic en el botón de siguiente.
	 * 
	 * @param event El evento de clic del ratón.
	 */
    @FXML
    void onSiguienteClicked(MouseEvent event) {
	    if(onSiguienteSkin != null) onSiguienteSkin.handle(event);
    }
	
	/**
	 * Establece el manejador para el evento de clic en el botón de siguiente.
	 * 
	 * @param onSiguienteSkin El manejador del evento de clic en el botón de siguiente.
	 */
	public void setOnSiguiente(EventHandler<MouseEvent> onSiguienteSkin) {
		this.onSiguienteSkin = onSiguienteSkin;
	}

	/**
	 * Cambia la imagen del skin.
	 * 
	 * @param image La nueva imagen del skin.
	 */
    public void cambiarSkin(Image image) {
    	skinImageView.setImage(image);
    }

	/**
	 * Método manejador para el evento de hacer clic en el botón de editar.
	 * 
	 * @param event El evento de clic del ratón.
	 */
    @FXML
    void onEditarClicked(MouseEvent event) {
    	if(onTuning != null) onTuning.handle(event);
	}
	
	/**
	 * Establece el manejador para el evento de clic en el botón de editar.
	 * 
	 * @param onTuning El manejador del evento de clic en el botón de editar.
	 */
	public void setOnEditar(EventHandler<MouseEvent> onTuning) {
		this.onTuning = onTuning;
	}

	/**
	 * Método manejador para el evento de hacer clic en el botón de jugar.
	 * 
	 * @param event El evento de clic del ratón.
	 */
    @FXML
    void onJugarClicked(MouseEvent event) {
    	if(onJugar != null) onJugar.handle(event);
	}
	
	/**
	 * Establece el manejador para el evento de clic en el botón de jugar.
	 * 
	 * @param onJugar El manejador del evento de clic en el botón de jugar.
	 */
	public void setOnJugar(EventHandler<MouseEvent> onJugar) {
		this.onJugar = onJugar;
	}
	
	/**
	 * Método manejador para el evento de arrastrar el ratón.
	 * 
	 * @param event El evento de arrastrar del ratón.
	 */
    @FXML
    void onMoverDragged(MouseEvent event) {
    	if(onMover != null) onMover.handle(event);
	}
	
	/**
	 * Establece el manejador para el evento de arrastrar el ratón.
	 * 
	 * @param onMover El manejador del evento de arrastrar el ratón.
	 */
	public void setOnMover(EventHandler<MouseEvent> onMover) {
		this.onMover = onMover;
	}

	/**
	 * Método manejador para el evento de presionar el ratón.
	 * 
	 * @param event El evento de presionar el ratón.
	 */
    @FXML
    void onMoverPressed(MouseEvent event) {
    	if(onMoverPressed != null) onMoverPressed.handle(event);
	}
	
	/**
	 * Establece el manejador para el evento de presionar el ratón.
	 * 
	 * @param onMoverPressed El manejador del evento de presionar el ratón.
	 */
	public void setOnMoverPressed(EventHandler<MouseEvent> onMoverPressed) {
		this.onMoverPressed = onMoverPressed;
	}

	/**
	 * Devuelve la imagen del skin actual.
	 * 
	 * @return La imagen del skin actual.
	 */
	public Image getSkinActual() {
		return skinActual;
	}
	
	/**
	 * Devuelve la imagen del complemento 1 actual.
	 * 
	 * @return La imagen del complemento 1 actual.
	 */
	public Image getComplemento1Actual() {
		return complemento1Actual;
	}
	
	/**
	 * Devuelve la imagen del complemento 2 actual.
	 * 
	 * @return La imagen del complemento 2 actual.
	 */
	public Image getComplemento2Actual() {
		return complemento2Actual;
	}
	
	/**
	 * Establece la imagen del skin actual.
	 * 
	 * @param skinActual La imagen del skin actual.
	 */
	public void setskinActual(Image skinActual) {
		this.skinActual = skinActual;
		skinImageView.setImage(skinActual);
	}
	
	/**
	 * Establece la imagen del complemento 1 actual.
	 * 
	 * @param complemento1Actual La imagen del complemento 1 actual.
	 */
	public void setcomplemento1Actual(Image complemento1Actual) {
		this.complemento1Actual = complemento1Actual;
		complemento1ImageView.setImage(complemento1Actual);
	}
	
	/**
	 * Establece la imagen del complemento 2 actual.
	 * 
	 * @param complemento2Actual La imagen del complemento 2 actual.
	 */
	public void setcomplemento2Actual(Image complemento2Actual) {
		this.complemento2Actual = complemento2Actual;
		complemento2ImageView.setImage(complemento2Actual);
	}

}
