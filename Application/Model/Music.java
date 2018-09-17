package Model;

import Util.*;

public class Music {

    public String name;
    public String lyrics;
    public int lang;

    public Music(String name, String lyrics, int lang) {
        this.name = name;
        this.lyrics = lyrics;
        this.lang = lang;
    }

    public String toString() {
        return this.name.toUpperCase() + "\n\n" + this.lyrics;
    }

}
