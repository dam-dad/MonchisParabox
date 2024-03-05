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
 * Controlador de la vista de ajustes de skins (tuning).
 */
public class tuningController implements Initializable {
    
    // Eventos
    private EventHandler<MouseEvent> onAtras;
    private EventHandler<MouseEvent> onSalir;
    private EventHandler<MouseEvent> onSonido;
    
    // Vistas
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
    
    /**
     * Obtiene la vista de ajustes de skins.
     *
     * @return La instancia de BorderPane que representa la vista de ajustes de skins.
     */
    public BorderPane getView() {
        return view;
    }
    
    /**
     * Constructor de la clase.
     */
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
        complemento1ImageView.setImage(new Image("/assets/complementos/c0.png"));
        complemento2ImageView.setImage(new Image("/assets/complementos/c0.png"));
        
        // Complemento 1
        complemento1xXImageView.setOnMouseClicked(event -> {
            if (onSonido != null) onSonido.handle(event);
            complemento1ImageView.setImage(new Image("/assets/complementos/c0.png"));
        });
        
        complemento1x1ImageView.setOnMouseClicked(event -> {
            if (onSonido != null) onSonido.handle(event);
            complemento1ImageView.setImage(new Image("/assets/complementos/c1.1.png"));
        });
        
        complemento1x2ImageView.setOnMouseClicked(event -> {
            if (onSonido != null) onSonido.handle(event);
            complemento1ImageView.setImage(new Image("/assets/complementos/c1.2.png"));
        });
        
        complemento1x3ImageView.setOnMouseClicked(event -> {
            if (onSonido != null) onSonido.handle(event);
            complemento1ImageView.setImage(new Image("/assets/complementos/c1.3.png"));
        });

        // Complemento 2
        complemento2xXImageView.setOnMouseClicked(event -> {
            if (onSonido != null) onSonido.handle(event);
            complemento2ImageView.setImage(new Image("/assets/complementos/c0.png"));
        });
        
        complemento2x1ImageView.setOnMouseClicked(event -> {
            if (onSonido != null) onSonido.handle(event);
            complemento2ImageView.setImage(new Image("/assets/complementos/c2.1.png"));
        });
        
        complemento2x2ImageView.setOnMouseClicked(event -> {
            if (onSonido != null) onSonido.handle(event);
            complemento2ImageView.setImage(new Image("/assets/complementos/c2.2.png"));
        });
        
        complemento2x3ImageView.setOnMouseClicked(event -> {
            if (onSonido != null) onSonido.handle(event);
            complemento2ImageView.setImage(new Image("/assets/complementos/c2.3.png"));
        });
    }

    /**
     * Establece el evento para la modificación de sonido.
     *
     * @param onSonido El evento para la modificación de sonido.
     */
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
    
    /**
     * Establece el evento para retroceder.
     *
     * @param onAtras El evento para retroceder.
     */
    public void setOnAtras(EventHandler<MouseEvent> onAtras) {
        this.onAtras = onAtras;
    }
    
    @FXML
    void onSalirClicked(MouseEvent event) {
        if(onSalir != null) onSalir.handle(event);
    }
    
    /**
     * Establece el evento para salir.
     *
     * @param onSalir El evento para salir.
     */
    public void setOnSalir(EventHandler<MouseEvent> onSalir) {
        this.onSalir = onSalir;
    }
    
    /**
     * Obtiene la imagen actual de la skin.
     *
     * @return La imagen actual de la skin.
     */
    public Image getskinActual() {
        return skinActual;
    }
    
    /**
     * Obtiene la imagen actual del complemento 1.
     *
     * @return La imagen actual del complemento 1.
     */
    public Image getcomplemento1Actual() {
        return complemento1Actual;
    }
    
    /**
     * Obtiene la imagen actual del complemento 2.
     *
     * @return La imagen actual del complemento 2.
     */
    public Image getcomplemento2Actual() {
        return complemento2Actual;
    }
    
    /**
     * Establece la imagen actual de la skin.
     *
     * @param skinActual La imagen actual de la skin.
     */
    public void setskinActual(Image skinActual) {
        this.skinActual = skinActual;
        skinImageView.setImage(skinActual);
    }
    
    /**
     * Establece la imagen actual del complemento 1.
     *
     * @param complemento1Actual La imagen actual del complemento 1.
     */
    public void setcomplemento1Actual(Image complemento1Actual) {
        this.complemento1Actual = complemento1Actual;
        complemento1ImageView.setImage(complemento1Actual);
    }
    
    /**
     * Establece la imagen actual del complemento 2.
     *
     * @param complemento2Actual La imagen actual del complemento 2.
     */
    public void setcomplemento2Actual(Image complemento2Actual) {
        this.complemento2Actual = complemento2Actual;
        complemento2ImageView.setImage(complemento2Actual);
    }
}

