package Model;

public class Translate {

    public String id;
    public String content;
    public int lang;

    public Translate(String id, String content, int lang) {
        this.id = id;
        this.content = content;
        this.lang = lang;
    }

    public String toString() {
        return this.content;
    }

}
