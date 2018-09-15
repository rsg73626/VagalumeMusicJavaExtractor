package Model;

import Util.*;

public class Music {

    public String name;
    public String lyrics;

    public Music(String name, String lyrics) {
        this.name = name;
        this.lyrics = lyrics;
    }

    public String toString() {
        return this.name.toUpperCase() + "\n\n" + this.lyrics;
    }

}
