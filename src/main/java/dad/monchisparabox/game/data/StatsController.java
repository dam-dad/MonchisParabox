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

public class StatsController {

    public static List<Stats> getListaStats() {
        String appDataFolder = System.getenv("APPDATA");
        String filePath = appDataFolder + "\\MonchisParabox\\stats.json";
        return cargarDesdeArchivo(filePath);
    }

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
