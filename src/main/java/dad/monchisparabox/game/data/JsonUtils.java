package dad.monchisparabox.game.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dad.monchisparabox.game.data.MapData;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {

    public static List<MapData> loadMapsFromJson(String filePath) {
        Gson gson = new Gson();
        List<MapData> maps = null;
        try (FileReader reader = new FileReader(filePath)) {
            Type mapListType = new TypeToken<List<MapData>>(){}.getType();
            maps = gson.fromJson(reader, mapListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maps;
    }
}

