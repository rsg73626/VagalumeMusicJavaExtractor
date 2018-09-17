package Model;

import java.util.List;
import java.util.ArrayList;
import Util.*;

public class Music {

    public String id;
    public String name;
    public String lyrics;
    public int lang;
    public List<Translate> translates = new ArrayList<Translate>();

    public Music(String id, String name, String lyrics, int lang) {
        this.id = id;
        this.name = name;
        this.lyrics = lyrics;
        this.lang = lang;
    }

    public String toString() {
        return this.name.toUpperCase() + "\n\n" + this.lyrics;
    }

}
