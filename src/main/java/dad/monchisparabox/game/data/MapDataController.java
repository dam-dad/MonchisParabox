package dad.monchisparabox.game.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controlador para cargar y acceder a los datos de los mapas del juego.
 */
public class MapDataController {

    private List<MapData> loadedMaps;
    private List<String> mapsToLoad = Arrays.asList("map1.json", "map2.json", "map3.json", "map4.json", "map5.json",
            "map6.json", "map7.json", "map8.json", "map9.json", "map10.json");

    /**
     * Constructor de la clase MapDataController. Carga los mapas desde archivos JSON.
     */
    public MapDataController() {
        loadedMaps = loadMapsFromJson("/maps/");
    }

    /**
     * Devuelve los mapas cargados en el controlador.
     *
     * @return Una lista de mapas cargados.
     */
    private List<MapData> getLoadedMaps() {
        return loadedMaps;
    }

    /**
     * Obtiene un mapa por su identificador.
     *
     * @param id El identificador del mapa.
     * @return El mapa correspondiente al identificador, o null si no se encuentra.
     */
    public MapData getMapById(int id) {
        return loadedMaps.stream().filter(map -> map.getId() == id).findFirst().orElse(null);
    }

    /**
     * Obtiene un mapa por su nombre.
     *
     * @param name El nombre del mapa.
     * @return El mapa correspondiente al nombre, o null si no se encuentra.
     */
    public MapData getMapByName(String name) {
        return loadedMaps.stream().filter(map -> map.getNombre().equals(name)).findFirst().orElse(null);
    }

    /**
     * Carga los mapas desde archivos JSON ubicados en la carpeta especificada.
     *
     * @param filePath La ruta de la carpeta que contiene los archivos JSON de los mapas.
     * @return Una lista de mapas cargados desde archivos JSON.
     */
    public List<MapData> loadMapsFromJson(String filePath) {
        List<MapData> maps = new ArrayList<>();
        Gson gson = new Gson();

        for (String mapFile : mapsToLoad) {
            try (InputStream inputStream = getClass().getResourceAsStream(filePath + mapFile);
                 InputStreamReader reader = new InputStreamReader(inputStream)) {
                Type mapListType = new TypeToken<MapData>() {}.getType();
                MapData mapData = gson.fromJson(reader, mapListType);
                maps.add(mapData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return maps;
    }
}
