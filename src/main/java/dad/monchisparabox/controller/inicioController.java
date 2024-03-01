package dad.proyectoDAD.controller;

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

public class inicioController implements Initializable {
	
	//actions
	private EventHandler<MouseEvent> onAjustes;
	private EventHandler<MouseEvent> onTuning;
	private EventHandler<MouseEvent> onJugar;
	private EventHandler<MouseEvent> onSiguienteSkin;
	private EventHandler<MouseEvent> onAnteriorSkin;
	private EventHandler<MouseEvent> onMover;
	private EventHandler<MouseEvent> onMoverPressed;
			
	// view
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
	
	public BorderPane getView() {
		return view;
	}
	
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
	
	@FXML
	void onAjustesClicked(MouseEvent event) {
		if(onAjustes != null) onAjustes.handle(event);
	}
	
	public void setOnAjustes(EventHandler<MouseEvent> onAjustes) {
		this.onAjustes = onAjustes;
	}

	
    @FXML
    void onAnteriorClicked(MouseEvent event) {
	    if(onAnteriorSkin != null) onAnteriorSkin.handle(event);
	}
	
	public void setOnAnterior(EventHandler<MouseEvent> onAnteriorSkin) {
		this.onAnteriorSkin = onAnteriorSkin;
	}
    

    @FXML
    void onSiguienteClicked(MouseEvent event) {
	    if(onSiguienteSkin != null) onSiguienteSkin.handle(event);
    }
	
	public void setOnSiguiente(EventHandler<MouseEvent> onSiguienteSkin) {
		this.onSiguienteSkin = onSiguienteSkin;
	}

    
    public void cambiarSkin(Image image) {
    	skinImageView.setImage(image);
    }

    @FXML
    void onEditarClicked(MouseEvent event) {
    	if(onTuning != null) onTuning.handle(event);
	}
	
	public void setOnEditar(EventHandler<MouseEvent> onTuning) {
		this.onTuning = onTuning;
	}

    @FXML
    void onJugarClicked(MouseEvent event) {
    	if(onJugar != null) onJugar.handle(event);
	}
	
	public void setOnJugar(EventHandler<MouseEvent> onJugar) {
		this.onJugar = onJugar;
	}
	

    @FXML
    void onMoverDragged(MouseEvent event) {
    	if(onMover != null) onMover.handle(event);
	}
	
	public void setOnMover(EventHandler<MouseEvent> onMover) {
		this.onMover = onMover;
	}

    @FXML
    void onMoverPressed(MouseEvent event) {
    	if(onMoverPressed != null) onMoverPressed.handle(event);
	}
	
	public void setOnMoverPressed(EventHandler<MouseEvent> onMoverPressed) {
		this.onMoverPressed = onMoverPressed;
	}

	
	
	public Image getskinActual() {
		return skinActual;
	}
	
	public Image getcomplemento1Actual() {
		return complemento1Actual;
	}
	
	public Image getcomplemento2Actual() {
		return complemento2Actual;
	}
	
	public void setskinActual(Image skinActual) {
		this.skinActual = skinActual;
		skinImageView.setImage(skinActual);
	}
	
	public void setcomplemento1Actual(Image complemento1Actual) {
		this.complemento1Actual = complemento1Actual;
		complemento1ImageView.setImage(complemento1Actual);
	}
	
	public void setcomplemento2Actual(Image complemento2Actual) {
		this.complemento2Actual = complemento2Actual;
		complemento2ImageView.setImage(complemento2Actual);
	}

}
