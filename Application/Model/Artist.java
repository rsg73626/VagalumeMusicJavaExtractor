package Model;

import java.util.List;
import java.util.ArrayList;

import Util.*;

public class Artist {

    public String name;
    public List<String> musicIds;

    public Artist(String name) {
        this.name = name;
        this.musicIds = new ArrayList<String>();
    }

    public String toString() {
        return this.name + "\nAmount of music ids: " + musicIds.size();
    }

}
