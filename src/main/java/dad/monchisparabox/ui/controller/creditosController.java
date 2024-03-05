package dad.monchisparabox.ui.controller;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controlador para la vista de los créditos del juego.
 */
public class creditosController implements Initializable {
	
	// Acciones
	private EventHandler<MouseEvent> onInicio;
	
	// Vista
    @FXML
    private ImageView image;
    
    @FXML
    private VBox vBox;

	@FXML
    private Label label;
	
	@FXML
	private BorderPane view;	
	
	
	public BorderPane getView() {
		return view;
	}
	
	/**
	 * Constructor por defecto.
	 * Carga la vista asociada al controlador.
	 */
	public creditosController() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreditosView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label.setWrapText(true);
		
		// Texto de los créditos
		label.setText("Monchi's parabox es un juego que narra la historia de un adolescente rebelde llamado Monchi, quien, por razones desconocidas, ha decidido fugarse del ciclo de desarrollo de aplicaciones multiplataforma y abandonar su amante Yorman. Sin embargo, después de reflexionar sobre su elección, decide darle una oportunidad a el amor y regresar a clase, sin saber que esto le llevará a embarcarse en una travesía llena de desafíos y misterios.\r\n"
				+ "\r\n"
				+ "La valentía y determinación de Monchi no conocen límites, y luchará incansablemente para resolver cada nivel y superar cada trauma de abandono, todo con el objetivo de regresar a clase y reunirse con su cuchi cuchi Yorman. Será necesario que Monchi se enfrente a su pasado, afrontando sus miedos más profundos y aprendiendo valiosas lecciones en su travesía.\r\n"
				+ "\r\n"
				+ "Cada nivel es un desafío único y representa un nuevo día en la vida de Monchi. A medida que avanza, deberá superar distintos desafíos homosexuales, sin embargo, lo más frustrante para él será que, una vez que complete un nivel, en vez de avanzar, aparecerá en el inicio de otro nivel en una estructura de niveles anidados, los cuales sumergirán al jugador en un viaje lleno de emociones intensas y desafíos estimulantes, sintiendo como si estuviera acompañando a el fokin Monchi en su búsqueda personal.\r\n"
				+ "\r\n"
				+ "Monchi's parabox posee una trama que refleja las decisiones y las segundas oportunidades que ofrece la vida. Además, busca enseñar a los jugadores la importancia de la educación, el amor, el crecimiento personal y el éxito en la vida académica. ¿Estás listo para ayudar al adolescente Monchi a enfrentar sus traumas infantiles y lograr su regreso triunfal a clase junto a su amado Yorman?\r\n"
				+ "\r\n\n"
				+ "¡Enhorabuena por haber logrado llegar hasta aquí! Tu perseverancia y dedicación han hecho posible que el amor entre Yorman y Monchi florezca una vez más. Ahora, juntos en el ciclo de desarrollo de aplicaciones multiplataforma, están más felices y unidos que nunca. ¡Gracias por haber sido parte de esta hermosa historia de amor!");
	
	}
	
	/**
	 * Método para iniciar la animación de desplazamiento hacia arriba de los créditos.
	 */
	public void subir() {
		TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(80), vBox);
		translateTransition.setByY(-1600); 

		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(event -> translateTransition.play()); 

		pause.play();
	}
	
	/**
	 * Método manejador para el evento de hacer clic en el botón de inicio.
	 * 
	 * @param event El evento de clic del ratón.
	 */
	@FXML
    void onInicioClicked(MouseEvent event) {
		if(onInicio != null) onInicio.handle(event);
	}
	
	/**
	 * Establece el manejador para el evento de inicio.
	 * 
	 * @param onInicio El manejador del evento de inicio.
	 */
	public void setOnInicio(EventHandler<MouseEvent> onInicio) {
		this.onInicio = onInicio;
	}
	
}

