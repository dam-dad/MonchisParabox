package dad.monchisparabox.controller;

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

public class ajustesController  implements Initializable {
	
	//actions
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
	
	//model
	private double volumen;
	
	// view 
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
	
	
	public BorderPane getView() {
		return view;
	}
	
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

			if(onVolumen != null) onVolumen.handle(null);
			
			if (volumen == 0) {
		    	VolumenMenosImageView.setImage(new Image("/images/volumen0.png"));
		    }else {
		    	VolumenMenosImageView.setImage(new Image("/images/volumen1.png"));
		    }
		});
		
	}
	
	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}
	
	public void setOnVolumen(EventHandler<MouseEvent> onVolumen) {
		this.onVolumen = onVolumen;
	}
	
	@FXML
	void onExpandirReducirClicked(MouseEvent event) {

		if(OnExpandir != null) OnExpandir.handle(event);
		
		String nombreImagen = expandirReducirImageView.getImage().getUrl().substring(expandirReducirImageView.getImage().getUrl().lastIndexOf("/") + 1);
		Stage stage = (Stage) view.getScene().getWindow();
		
		if (nombreImagen.equals("expandir.png")) {
			expandirReducirImageView.setImage(new Image("/images/reducir.png"));
	        stage.setMaximized(true);
		    
		}else if (nombreImagen.equals("reducir.png")) {
			expandirReducirImageView.setImage(new Image("/images/expandir.png"));
			stage.setMaximized(false);
			stage.setHeight(522);
			stage.setWidth(928);
		}
	}
	
	public void setOnExpandir(EventHandler<MouseEvent> OnExpandir) {
		this.OnExpandir = OnExpandir;
	}
	
	@FXML
	void OnAtrasClicked(MouseEvent event) {
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
	
	@FXML
    void onDiplomaClicked(MouseEvent event) {
		if(onPdf != null) onPdf.handle(event);
    }

    @FXML
    void onDiplomaLabelClicked(MouseEvent event) {
    	if(onPdf != null) onPdf.handle(event);
    }

    public void setOnPdf(EventHandler<MouseEvent> onPdf) {
		this.onPdf = onPdf;
	}
    
    @FXML
    void onEfectosClicked(MouseEvent event) {
		String nombreImagen = efectosImageView.getImage().getUrl().substring(efectosImageView.getImage().getUrl().lastIndexOf("/") + 1);
		
		if (nombreImagen.equals("volumen3.png")) {
		    efectosImageView.setImage(new Image("/images/volumen4.png"));
		    if(onOffEfectos != null) onOffEfectos.handle(event);
		    
		}else if (nombreImagen.equals("volumen4.png")) {
		    efectosImageView.setImage(new Image("/images/volumen3.png"));
		    if(onOnEfectos != null) onOnEfectos.handle(event);
		}
    }
    
    public void setonOffEfectos(EventHandler<MouseEvent> onOffEfectos) {
		this.onOffEfectos = onOffEfectos;
	}
    
    public void setonOnEfectos(EventHandler<MouseEvent> onOnEfectos) {
		this.onOnEfectos = onOnEfectos;
	}
    
    @FXML
    void onMusicaClicked(MouseEvent event) {
		String nombreImagen = musicaImageView.getImage().getUrl().substring(musicaImageView.getImage().getUrl().lastIndexOf("/") + 1);
		
		if (nombreImagen.equals("volumen3.png")) {
		    Image nuevaImagen = new Image("/images/volumen4.png");
		    musicaImageView.setImage(nuevaImagen);
		    if(onOffMusica != null) onOffMusica.handle(event);
		    
		}else if (nombreImagen.equals("volumen4.png")) {
		    Image nuevaImagen = new Image("/images/volumen3.png");
		    musicaImageView.setImage(nuevaImagen);
		    if(onOnMusica != null) onOnMusica.handle(event);
		}
    }
    
    public void setonOffMusica(EventHandler<MouseEvent> onOffMusica) {
		this.onOffMusica = onOffMusica;
	}
    
    public void setonOnMusica(EventHandler<MouseEvent> onOnMusica) {
		this.onOnMusica = onOnMusica;
	}

    @FXML
    void onFlechasClicked(MouseEvent event) {
    	String nombreImagen = flechasImageView.getImage().getUrl().substring(flechasImageView.getImage().getUrl().lastIndexOf("/") + 1);
		
		if (nombreImagen.equals("teclasFlechas.png")) {
			flechasImageView.setImage(new Image("/images/teclasFlechasX.png"));
			letrasImageView.setImage(new Image("/images/teclasLetras.png"));
			
		    if(onFlechas != null) onFlechas.handle(event);
		}
    }
    
    public void setonFlechas(EventHandler<MouseEvent> onFlechas) {
		this.onFlechas = onFlechas;
	}

    @FXML
    void onLetrasClicked(MouseEvent event) {
    	String nombreImagen = letrasImageView.getImage().getUrl().substring(letrasImageView.getImage().getUrl().lastIndexOf("/") + 1);
		
		if (nombreImagen.equals("teclasLetras.png")) {
			letrasImageView.setImage(new Image("/images/teclasLetrasX.png"));
			flechasImageView.setImage(new Image("/images/teclasFlechas.png"));
			if(onLetras != null) onLetras.handle(event);
		}
    }
    
    public void setonLetras(EventHandler<MouseEvent> onLetras) {
		this.onLetras = onLetras;
	}

    @FXML
    void onVolumenMasClicked(MouseEvent event) {
	    double nuevoValor = volumenSlider.getValue() + 15;
	    
	    VolumenMenosImageView.setImage(new Image("/images/volumen1.png"));
	    
		setVolumen(nuevoValor);
	    volumenSlider.setValue(nuevoValor);
		if(onVolumen != null) onVolumen.handle(event);
    }

    @FXML
    void onVolumenMenosClicked(MouseEvent event) {
	    double nuevoValor = volumenSlider.getValue() - 15;
	    
	    if (nuevoValor < 0){
	    	nuevoValor = 0;
	    }
	    
	    if (nuevoValor == 0) {
	    	VolumenMenosImageView.setImage(new Image("/images/volumen0.png"));
	    }else {
	    	VolumenMenosImageView.setImage(new Image("/images/volumen1.png"));
	    }
	    
		setVolumen(nuevoValor);
	    volumenSlider.setValue(nuevoValor);
		if(onVolumen != null) onVolumen.handle(event);
    }

}
