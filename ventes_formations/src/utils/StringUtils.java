package utils;

/**
 * Class for manipulation of String
 */
public class StringUtils {
    private StringUtils() {
        /* This utility class should not be instantiated */
    }

    /**
     * Return the string with the len of size
     *
     * @param string
     * @param size
     * @return
     */
    public static String padOrTrunc(String string, int size) {
        if (string.length() > size) {
            return string.substring(0, size);
        }
        StringBuilder res = new StringBuilder();
        res.append(string);
        while (res.length() != size) {
            res.append(" ");
        }
        return res.toString();
    }

    /**
     * Repeat a string x times
     *
     * @param string
     * @param number
     * @return
     */
    public static String repeat(String string, int number) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < number; i++) {
            res.append(string);
        }
        return res.toString();
    }
}
