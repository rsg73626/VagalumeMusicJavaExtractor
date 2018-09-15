import java.io.*;
import java.util.*;

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

    public static void main(String args[]) {

        for (String letter : LETTERS) {
            Util.print(letter);
            List<String> artistNames = JSONManager.readArtistsList(letter);
            for (String artistName : artistNames) {
                Util.print(artistName);
                Artist artist = VagalumeAPI.readArtistByName(artistName);
                int count = 1;
                for (String musicId : artist.musicIds) {
                    Util.print(count + " de " + artist.musicIds.size());
                    Music music = VagalumeAPI.readMusicById(musicId);
                    TXTManager.createFile(count++ + " - " + music.name, music.toString(), letter + "/" + artistName);
                    if (count > 5) { return; }
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
