package Util;

import java.util.*;

public class Util {

    public static void print(Object content) {
        System.out.println(content);
    }

    public static String[] reverse(String[] array) {
        String[] copy = Arrays.copyOf(array, array.length);
        List<String> reversed = Arrays.asList(copy);
        Collections.reverse(reversed);
        return reversed.toArray(copy);
    }

}
