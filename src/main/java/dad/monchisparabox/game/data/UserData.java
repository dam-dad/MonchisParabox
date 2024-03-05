package dad.monchisparabox.game.data;

import javafx.scene.image.Image;

/**
 * Clase que representa los datos del usuario del juego.
 */
public class UserData {

    private Image skin;
    private boolean musica;
    private boolean efectos;
    private double volumen;
    private boolean letras;

    /**
     * Constructor de la clase UserData.
     *
     * @param skin    La imagen de la piel seleccionada por el usuario.
     * @param musica  Indica si la música está habilitada o no.
     * @param efectos Indica si los efectos de sonido están habilitados o no.
     * @param volumen El volumen de audio seleccionado por el usuario.
     * @param letras  Indica si se muestran las letras en el juego.
     */
    public UserData(Image skin, boolean musica, boolean efectos, double volumen, boolean letras) {
        this.skin = skin;
        this.efectos = efectos;
        this.musica = musica;
        this.volumen = volumen;
        this.letras = letras;
    }

    /**
     * Obtiene la imagen de la piel seleccionada por el usuario.
     *
     * @return La imagen de la piel.
     */
    public Image getSkin() {
        return skin;
    }

    /**
     * Obtiene el volumen de audio seleccionado por el usuario.
     *
     * @return El volumen de audio.
     */
    public double getVolumen() {
        return volumen;
    }

    /**
     * Verifica si la música está habilitada.
     *
     * @return true si la música está habilitada, false de lo contrario.
     */
    public boolean musicaEnabled() {
        return musica;
    }

    /**
     * Verifica si los efectos de sonido están habilitados.
     *
     * @return true si los efectos de sonido están habilitados, false de lo contrario.
     */
    public boolean efectosEnabled() {
        return efectos;
    }

    /**
     * Verifica si se muestran las letras en el juego.
     *
     * @return true si se muestran las letras, false de lo contrario.
     */
    public boolean letras() {
        return letras;
    }
}
