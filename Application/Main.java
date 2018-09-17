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

            Util.print(" - " + letter + " - ");

            List<String> artistNames = JSONManager.readArtistsList(letter);
            for (String artistName : artistNames) {

                UUtil.print(" - " + artistName.toUpperCase() + " - ");

                Artist artist = VagalumeAPI.readArtistByName(artistName);
                int lyricsCount = 1;
                for (String musicId : artist.musicIds) {

                    Util.print("Mus: " + lyricsCount + " de " + artist.musicIds.size() + " | Reqs: " + requestCount + " de " + 50);

                    Music music = VagalumeAPI.readMusicById(musicId);

                    String fileName = lyricsCount + " - " + music.name + " - " + music.id;
                    String lyricsPath = LANGUAGES[music.lang > 9 ? 9 : music.lang-1] + "/" + letter + "/" + artistName;

                    TXTManager.createMusicFile(fileName, music.toString(), lyricsPath);

                    for (Translate translate : music.translates) {
                        fileName += " - " + translate.id;
                        String translatePath = LANGUAGES[translate.lang > 9 ? 9 : translate.lang-1] + "/" + letter + "/" + artistName;
                        TXTManager.createTranslateFile(fileName, translate.toString(), translatePath);
                    }

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
