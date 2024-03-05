package dad.monchisparabox.game.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona la carga y guardado de estadísticas del juego.
 */
public class StatsController {

    /**
     * Obtiene la lista de estadísticas guardadas.
     *
     * @return La lista de estadísticas.
     */
    public static List<Stats> getListaStats() {
        String appDataFolder = System.getenv("APPDATA");
        String filePath = appDataFolder + "\\MonchisParabox\\stats.json";
        return cargarDesdeArchivo(filePath);
    }

    /**
     * Guarda las estadísticas de una partida en el archivo JSON.
     *
     * @param mapaObjetivo      El ID del mapa de la partida.
     * @param tiempo            El tiempo transcurrido en la partida.
     * @param nuevosMovimientos El número de movimientos realizados en la partida.
     */
    public static void guardar(int mapaObjetivo, String tiempo, int nuevosMovimientos) {
        // Cargar datos desde el archivo JSON
        String appDataFolder = System.getenv("APPDATA");
        String filePath = appDataFolder + "\\MonchisParabox\\stats.json";

        List<Stats> listaStats = cargarDesdeArchivo(filePath);
        // Modificar el tiempo y los movimientos de un mapa específico o crearlo si no existe
        if (listaStats != null) {
            // Buscar el mapa en la lista
            boolean mapaEncontrado = false;
            for (Stats stats : listaStats) {
                if (stats.idMapa == mapaObjetivo) {
                    stats.setTiempoMovimientos(tiempo, nuevosMovimientos);
                    mapaEncontrado = true;
                    break;
                }
            }

            // Si el mapa no se encontró, crear un nuevo objeto Stats y añadirlo a la lista
            if (!mapaEncontrado) {
                Stats nuevoMapa = new Stats(mapaObjetivo, tiempo, nuevosMovimientos);
                listaStats.add(nuevoMapa);
            }

            // Guardar la lista actualizada en el archivo JSON
            guardarEnArchivo(listaStats, filePath);
        }
    }

    /**
     * Carga las estadísticas desde un archivo JSON.
     *
     * @param nombreArchivo El nombre del archivo JSON.
     * @return La lista de estadísticas cargadas desde el archivo.
     */
    private static List<Stats> cargarDesdeArchivo(String nombreArchivo) {
        try (FileReader fileReader = new FileReader(nombreArchivo)) {
            // Crear un tipo para representar la lista de Stats
            Type listaStatsType = new TypeToken<List<Stats>>() {}.getType();

            // Deserializar el JSON a una lista de Stats
            return new Gson().fromJson(fileReader, listaStatsType);
        } catch (IOException e) {
            // Si hay un error al cargar el archivo, devolver una lista vacía
            return new ArrayList<>();
        }
    }

    /**
     * Guarda las estadísticas en un archivo JSON.
     *
     * @param listaStats    La lista de estadísticas a guardar.
     * @param nombreArchivo El nombre del archivo JSON.
     */
    private static void guardarEnArchivo(List<Stats> listaStats, String nombreArchivo) {
        try (FileWriter fileWriter = new FileWriter(nombreArchivo)) {
            // Convertir la lista a JSON y guardar en el archivo
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(listaStats);
            fileWriter.write(json);

            System.out.println("Datos actualizados y guardados correctamente en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

