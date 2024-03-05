package dad.monchisparabox;

import javafx.application.Application;

import java.io.File;

/**
 * Clase principal que contiene el método main para iniciar la aplicación Monchi's Parabox.
 */
public class Main {

    /**
     * Método principal que inicia la aplicación Monchi's Parabox.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en esta aplicación)
     */
    public static void main(String[] args) {
        // Obtener la carpeta de datos de la aplicación del entorno del sistema
        String appDataFolder = System.getenv("APPDATA");

        // Construir la ruta del archivo de datos de la aplicación
        String filePath = appDataFolder + "\\MonchisParabox\\";

        // Comprobar si la carpeta de datos de la aplicación existe, si no, crearla
        if (!new File(filePath).exists()) {
            new File(filePath).mkdir();
            new File(filePath + "\\generated").mkdir();
        }

        // Iniciar la aplicación Monchi's Parabox
        Application.launch(App.class, args);
    }
}
