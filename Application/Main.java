import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import Model.*;
import FileManager.*;
import Util.*;

public class Main {

    private static final String[] LETTERS = new String[]
    {
        "#", "A",
        "B", "C", "D", "E", "F",
        "G", "H", "I", "J", "K",
        "L", "M", "N", "O", "P",
        "Q", "R", "S", "T", "U",
        "V", "W", "X", "Y", "Z"
    };

    private static final String[] LANGUAGES = new String[]
    {
        "1 - Portuguese - Brazil", "2 - English", "3 - Spanish", "4 - French", "5 - German",
        "6 - Italian", "7 - Dutch", "8 - Japanese", "9 - Portuguese Portugal", "10 - Others (999999)"
    };

    public static void main(String args[]) {
        int requestCount = 1;
        int requestCicle = 1;
        Util.print(" - REQUEST CICLE: " + requestCicle + " - ");
        for (String letter : LETTERS) {
            Util.print(letter);
            List<String> artistNames = JSONManager.readArtistsList(letter);
            for (String artistName : artistNames) {
                Util.print(artistName);
                Artist artist = VagalumeAPI.readArtistByName(artistName);
                int lyricsCount = 1;
                for (String musicId : artist.musicIds) {
                    Util.print(lyricsCount + " de " + artist.musicIds.size() + " (" + requestCount + ")");
                    Music music = VagalumeAPI.readMusicById(musicId);
                    TXTManager.createFile(lyricsCount + " - " + music.name, music.toString(), LANGUAGES[music.lang > 9 ? 9 : music.lang-1] + "/" + letter + "/" + artistName);
                    if (requestCount >= 50) {
                        Util.print(" - PAUSE - ");
                        try { TimeUnit.SECONDS.sleep(60 * 2); } catch (Exception e) { Util.print("Error while waiting: " + e.getLocalizedMessage()); }
                        requestCount = 0;
                        requestCicle++;
                        Util.print(" - REQUEST CICLE: " + requestCicle + " - ");
                    }
                    requestCount++;
                    lyricsCount++;
                }
            }
        }

        // Artist adele = VagalumeAPI.readArtistByName("Adele()");
        // Util.print(adele.toString());
        // int i = 1;
        // for (String id : adele.musicIds) {
        //     Util.print(" *** " + i++ + ": " + id + " *** ");
        //     Music music = VagalumeAPI.readMusicById(id);
        //     Util.print(music.toString());
        // }

        // Music music = VagalumeAPI.readMusicById("3ade68b8ga0fcefa3");
        // Util.print(music.toString());

    }

}
