package dad.monchisparabox.game.data;

import javafx.scene.image.Image;

public class UserData {

    private Image skin;
    private boolean musica;
    private boolean efectos;
    private double volumen;
    private boolean letras;

    public UserData(Image skin, boolean musica, boolean efectos, double volumen, boolean letras) {
        this.skin = skin;
        this.efectos = efectos;
        this.musica = musica;
        this.volumen = volumen;
        this.letras = letras;
    }

    public Image getSkin() {
        return skin;
    }

    public double getVolumen() {
        return volumen;
    }

    public boolean musicaEnabled() {
        return musica;
    }

    public boolean efectosEnabled() {
        return efectos;
    }

    public boolean letras() {
        return letras;
    }
}
