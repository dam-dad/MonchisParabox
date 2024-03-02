package dad.monchisparabox.controller;

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

public class tuningController implements Initializable {
	
	//actions
	private EventHandler<MouseEvent> onAtras;
	private EventHandler<MouseEvent> onSalir;
	private EventHandler<MouseEvent> onSonido;
	
	// view 
	private Image skinActual;
	private Image complemento1Actual;
	private Image complemento2Actual;
	
	@FXML
    private ImageView atrasImageView;

    @FXML
    private ImageView complemento1ImageView;

    @FXML
    private ImageView complemento1x1ImageView;

    @FXML
    private ImageView complemento1x2ImageView;

    @FXML
    private ImageView complemento1x3ImageView;

    @FXML
    private ImageView complemento1xXImageView;

    @FXML
    private ImageView complemento2ImageView;

    @FXML
    private ImageView complemento2x1ImageView;

    @FXML
    private ImageView complemento2x2ImageView;

    @FXML
    private ImageView complemento2x3ImageView;

    @FXML
    private ImageView complemento2xXImageView;

    @FXML
    private ImageView salirImageView;

    @FXML
    private ImageView skinImageView;

    
	@FXML
	private BorderPane view;	
	
	public BorderPane getView() {
		return view;
	}
	
	public tuningController() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TuningView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		complemento1ImageView.setImage(new Image("/images/complementos/c0.png"));
		complemento2ImageView.setImage(new Image("/images/complementos/c0.png"));
		
		//COMPLEMENTO 1
		
		complemento1xXImageView.setOnMouseClicked(event -> {
			if(onSonido != null) onSonido.handle(event);
			complemento1ImageView.setImage(new Image("/images/complementos/c0.png"));
		});
		
		complemento1x1ImageView.setOnMouseClicked(event -> {
			if(onSonido != null) onSonido.handle(event);
			complemento1ImageView.setImage(new Image("/images/complementos/c1.1.png"));
		});
		
		complemento1x2ImageView.setOnMouseClicked(event -> {
			if(onSonido != null) onSonido.handle(event);
			complemento1ImageView.setImage(new Image("/images/complementos/c1.2.png"));
		});
		
		complemento1x3ImageView.setOnMouseClicked(event -> {
			if(onSonido != null) onSonido.handle(event);
			complemento1ImageView.setImage(new Image("/images/complementos/c1.3.png"));
		});

		
		//COMPLEMENTO 2
		
		complemento2xXImageView.setOnMouseClicked(event -> {
			if(onSonido != null) onSonido.handle(event);
			complemento2ImageView.setImage(new Image("/images/complementos/c0.png"));
		});
		
		complemento2x1ImageView.setOnMouseClicked(event -> {
			if(onSonido != null) onSonido.handle(event);
			complemento2ImageView.setImage(new Image("/images/complementos/c2.1.png"));
		});
		
		complemento2x2ImageView.setOnMouseClicked(event -> {
			if(onSonido != null) onSonido.handle(event);
			complemento2ImageView.setImage(new Image("/images/complementos/c2.2.png"));
		});
		
		complemento2x3ImageView.setOnMouseClicked(event -> {
			if(onSonido != null) onSonido.handle(event);
			complemento2ImageView.setImage(new Image("/images/complementos/c2.3.png"));
		});
		
	}

	public void setOnSonido(EventHandler<MouseEvent> onSonido) {
		this.onSonido = onSonido;
	}
	
	@FXML
	void OnAtrasClicked(MouseEvent event) {
		skinActual = new Image(skinImageView.getImage().getUrl());
		complemento1Actual = new Image(complemento1ImageView.getImage().getUrl());
		complemento2Actual= new Image(complemento2ImageView.getImage().getUrl());
				
		if(onAtras != null) onAtras.handle(event);
	}
	
	public void setOnAtras(EventHandler<MouseEvent> onAtras) {
		this.onAtras = onAtras;
	}
	
	
	@FXML
    void onSalirClicked(MouseEvent event) {
		if(onSalir != null) onSalir.handle(event);
	}
	
	public void setOnSalir(EventHandler<MouseEvent> onSalir) {
		this.onSalir = onSalir;
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

