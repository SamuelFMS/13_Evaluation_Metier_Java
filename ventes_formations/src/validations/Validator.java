package validations;

/**
 * Class containing all the validation regex
 */
public class Validator {
    /**
     * Check if the number that as been input have 10 decimal with potentially 2 number after
     */
    public static final String DECIMAL = "^\\d{1,8}(.\\d{1,2})?$";

    private Validator() {
        /* This utility class should not be instantiated */
    }


    public static boolean isAValidDecimal(String decimal) {
        return decimal.matches(DECIMAL);
    }
}
