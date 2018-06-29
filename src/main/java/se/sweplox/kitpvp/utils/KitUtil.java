package se.sweplox.kitpvp.utils;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KitUtil {

    public static String toString(Set<String> kits) {
        String string = null;
        for(String kit : kits) {
            string = string == null ? kit : string + ":" + kit;
        }
        return string;
    }

    public static Set<String> fromString(String string) {
        String[] array = string.split(":");
        return new HashSet<>(Arrays.asList(array));
    }
}
