package FileManager;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringEscapeUtils;
import org.json.*;

import Model.*;
import Util.*;

public class JSONManager {

    public static List<String> readArtistsList(String fileName) {
        try {
            String path = System. getProperty("user.dir") + "/../Resources/Artists/" + fileName + ".json";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            Gson gson = new Gson();
            Object json = gson.fromJson(bufferedReader, Object.class);
            return (ArrayList<String>)json;
        } catch (FileNotFoundException e) {
            Util.print("Error while trying to read file: " + fileName + ".json");
        }

        return null;
    }

    public static Artist convertToArtist(String jsonString) {
        jsonString = StringEscapeUtils.unescapeJava(jsonString);
        JSONObject obj = new JSONObject(jsonString);
        JSONObject artist = obj.getJSONObject("artist");

        Artist a = new Artist(artist.getString("desc"));

        JSONObject lyrics = artist.getJSONObject("lyrics");
        JSONArray item = lyrics.getJSONArray("item");

        for (int i = 0; i < item.length(); i++) {
            a.musicIds.add(((JSONObject)item.get(i)).getString("id"));
        }

        return a;
    }

    public static Music convertToMusic(String jsonString) {
        JSONObject obj = new JSONObject(jsonString);
        JSONArray musicsArray = obj.getJSONArray("mus");
        JSONObject musicObject = (JSONObject) musicsArray.get(0);

        Music music = new Music(musicObject.getString("id"),
                         StringEscapeUtils.unescapeJava(musicObject.getString("name")),
                         StringEscapeUtils.unescapeJava(musicObject.getString("text")),
                         musicObject.getInt("lang"));

        if (musicObject.has("translate")) {
            JSONArray translateArray = (JSONArray) musicObject.getJSONArray("translate");
            for (int i = 0; i < translateArray.length(); i++) {
                JSONObject translateObject = (JSONObject) translateArray.get(i);
                Translate translate = new Translate(translateObject.getString("id"),
                                                    StringEscapeUtils.unescapeJava(translateObject.getString("text")),
                                                    translateObject.getInt("lang"));
                music.translates.add(translate);
            }
        }

        return music;
    }

}
