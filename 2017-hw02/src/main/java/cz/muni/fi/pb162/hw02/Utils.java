package cz.muni.fi.pb162.hw02;

/**
 * @author Karel Vaculik
 */
public class Utils {

    /**
     * Test, if the given character is valid name character in the context of a query.
     *
     * @param c - the character
     * @return true, if the character is valid, else false
     */
    public static boolean isNameChar(char c) {
        return Character.isLetterOrDigit(c) || c == '_' || c == '-';
    }
}
