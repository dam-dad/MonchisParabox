package dad.monchisparabox;

import javafx.application.Application;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		String appDataFolder = System.getenv("APPDATA");
		String filePath = appDataFolder + "\\MonchisParabox\\";

		if(!new File(filePath).exists()) {
			new File(filePath).mkdir();
		}

		Application.launch(App.class, args);
	}
}
