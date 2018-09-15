import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import com.google.gson.*;

import Model.*;
import FileManager.*;
import Util.*;

public class VagalumeAPI {

    private static final String ARTIST_URL = "https://www.vagalume.com.br/"; // nomeArtista/index.js
    private static final String ARTIST_URL_2 = "/index.js";
    private static final String MUSIC_URL = "https://api.vagalume.com.br/search.php?musid="; // idDaMusica
    private static Map<String, List<String>> specialCharacters;

    static Artist readArtistByName(String name) {
        if (specialCharacters == null) { setSpecialCharacters(); }
        try {
            String stringURL = ARTIST_URL + formatArtistName(name) + ARTIST_URL_2;

            URL url = new URL(stringURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            int responseCode = connection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    		String inputLine;
    		StringBuffer response = new StringBuffer();

    		while ((inputLine = in.readLine()) != null) { response.append(inputLine); }
    		in.close();

            return JSONManager.convertToArtist(response.toString());
        } catch (Exception e) {
            Util.print("Error while trying read artist " + name + ": " + e.getLocalizedMessage());
        }
        return null;
    }

    static Music readMusicById(String id) {
        try {
            String stringURL = MUSIC_URL + id;

            URL url = new URL(stringURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            int responseCode = connection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    		String inputLine;
    		StringBuffer response = new StringBuffer();

    		while ((inputLine = in.readLine()) != null) { response.append(inputLine); }
    		in.close();

            return JSONManager.convertToMusic(response.toString());
        } catch (Exception e) {
            Util.print("Error while trying read music " + id + ": " + e.getLocalizedMessage());
        }
        return null;
    }

    private static void setSpecialCharacters() {
        specialCharacters = new HashMap<String, List<String>>();
        specialCharacters.put("", Arrays.asList(new String[] {":", "/", "%", "&", "\\(", "\\)", "!", "º", "ª", "'", "\\*"} ));
        specialCharacters.put("-", Arrays.asList(new String[] {"\\ ", "\\.", "\\,", "\\+"} ));
        specialCharacters.put("a", Arrays.asList(new String[] {"á", "â", "à", "å", "ã", "ä"} ));
        specialCharacters.put("e", Arrays.asList(new String[] {"é", "ê", "è", "ë"} ));
        specialCharacters.put("i", Arrays.asList(new String[] {"í", "î", "ì", "ï"} ));
        specialCharacters.put("o", Arrays.asList(new String[] {"ó", "ô", "ò", "ø", "õ", "ö"} ));
        specialCharacters.put("u", Arrays.asList(new String[] {"ú", "û", "ù", "ü"} ));
        specialCharacters.put("c", Arrays.asList(new String[] {"ç"} ));
        specialCharacters.put("n", Arrays.asList(new String[] {"ñ"} ));
    }

    private static String formatArtistName(String string) {
        string = string.toLowerCase();
        for (Map.Entry<String, List<String>> entry : specialCharacters.entrySet()) {
            // Util.print(" *** " + entry.getKey() + " *** ");
            for (String value : entry.getValue()) {
                // Util.print(value);
                string = string.replaceAll(value, entry.getKey());
                // Util.print(string);
            }
        }
        return string;
    }

}
