package dad.proyectoDAD.skin;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Skin extends ImageView{

	private Image skinFinal;
	
	private Image skin;
	
	private Image complemento1;
	
	private Image complemento2;

	public Image getSkinFinal() {
		return skinFinal;
	}

	public void setSkinFinal(Image skinFinal) {
		this.skinFinal = skinFinal;
	}
	
	public Image getSkin() {
		return skin;
	}

	public void setSkin(Image skin) {
		this.skin = skin;
	}

	public Image getComplemento1() {
		return complemento1;
	}

	public void setComplemento1(Image complemento1) {
		this.complemento1 = complemento1;
	}

	public Image getComplemento2() {
		return complemento2;
	}

	public void setComplemento2(Image complemento2) {
		this.complemento2 = complemento2;
	}
}
