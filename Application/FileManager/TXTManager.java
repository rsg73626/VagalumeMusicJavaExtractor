package FileManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import Util.*;

public class TXTManager {

    public static void createFile(String name, String content, String path) {

        File f = new File(System.getProperty("user.dir") + "/../Resources/Musics/" + path + "/" + name + ".txt");
        f.getParentFile().mkdirs();
        try {
            f.createNewFile();
        } catch (Exception e) {
            System.err.println("already exists: " + e.getMessage());
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
            writer.write(content);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.err.println("error while writing on file " + e.getMessage());
        }

    }

}
