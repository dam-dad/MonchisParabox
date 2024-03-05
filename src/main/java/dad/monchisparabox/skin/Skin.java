package dad.monchisparabox.skin;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Clase que representa la piel de un personaje en el juego Monchi's Parabox.
 */
public class Skin extends ImageView {

    private Image skinFinal;
    private Image skin;
    private Image complemento1;
    private Image complemento2;

    /**
     * Obtiene la imagen final de la piel.
     *
     * @return La imagen final de la piel.
     */
    public Image getSkinFinal() {
        return skinFinal;
    }

    /**
     * Establece la imagen final de la piel.
     *
     * @param skinFinal La imagen final de la piel a establecer.
     */
    public void setSkinFinal(Image skinFinal) {
        this.skinFinal = skinFinal;
    }

    /**
     * Obtiene la imagen base de la piel.
     *
     * @return La imagen base de la piel.
     */
    public Image getSkin() {
        return skin;
    }

    /**
     * Establece la imagen base de la piel.
     *
     * @param skin La imagen base de la piel a establecer.
     */
    public void setSkin(Image skin) {
        this.skin = skin;
    }

    /**
     * Obtiene el primer complemento de la piel.
     *
     * @return El primer complemento de la piel.
     */
    public Image getComplemento1() {
        return complemento1;
    }

    /**
     * Establece el primer complemento de la piel.
     *
     * @param complemento1 El primer complemento de la piel a establecer.
     */
    public void setComplemento1(Image complemento1) {
        this.complemento1 = complemento1;
    }

    /**
     * Obtiene el segundo complemento de la piel.
     *
     * @return El segundo complemento de la piel.
     */
    public Image getComplemento2() {
        return complemento2;
    }

    /**
     * Establece el segundo complemento de la piel.
     *
     * @param complemento2 El segundo complemento de la piel a establecer.
     */
    public void setComplemento2(Image complemento2) {
        this.complemento2 = complemento2;
    }
}
