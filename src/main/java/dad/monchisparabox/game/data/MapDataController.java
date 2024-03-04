package dad.monchisparabox.game.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapDataController {

    private List<MapData> loadedMaps;
    private List<String> mapsToLoad = Arrays.asList("map1.json", "map2.json","map3.json", "map4.json", "map5.json", "map6.json", "map7.json", "map8.json", "map9.json", "map10.json");

    public MapDataController() {
        loadedMaps = loadMapsFromJson("/maps/");
    }

    private List<MapData> getLoadedMaps() {
        return loadedMaps;
    }

    public MapData getMapById(int id) {
        return loadedMaps.stream().filter(map -> map.getId() == id).findFirst().orElse(null);
    }

    public MapData getMapByName(String name) {
        return loadedMaps.stream().filter(map -> map.getNombre().equals(name)).findFirst().orElse(null);
    }

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
