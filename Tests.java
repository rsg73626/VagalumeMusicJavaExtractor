import java.util.*;
import Application.*;

public class Tests {

    public static void main(String[] args) {

        Map<String, List<String>> specialCharacters;
        specialCharacters = new HashMap<String, List<String>>();
        specialCharacters.put("a", Arrays.asList(new String[] {"á", "â", "à", "å", "ã", "ä"} ));
        specialCharacters.put("e", Arrays.asList(new String[] {"é", "ê", "è", "ë"} ));
        specialCharacters.put("i", Arrays.asList(new String[] {"í", "î", "ì", "ï"} ));
        specialCharacters.put("o", Arrays.asList(new String[] {"ó", "ô", "ò", "ø", "õ", "ö"} ));
        specialCharacters.put("u", Arrays.asList(new String[] {"ú", "û", "ù", "ü"} ));
        specialCharacters.put("c", Arrays.asList(new String[] {"ç"} ));
        specialCharacters.put("n", Arrays.asList(new String[] {"ñ"} ));

        String originalString = "áâàéêèíîìóôòúûùçñ";

        Util.print(originalString);

        for (Map.Entry<String, List<String>> entry : specialCharacters.entrySet()) {
            Util.print(" *** " + entry.getKey() + " *** ");
            for (String value : entry.getValue()) {
                Util.print(value);
                originalString = originalString.replaceAll(value, entry.getKey());
            }
        }

        Util.print(originalString);

    }

}
