package utils;

import validation.Validator;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputUtils {
    private InputUtils() {
        /* This utility class should not be instantiated */
    }

    /**
     * Read a boolean and return it yes or no y/n
     *
     * @param scanner
     * @return
     */
    public static boolean readBoolean(Scanner scanner) {
        boolean result = false;
        boolean isInputValid = false;
        while (!isInputValid) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                isInputValid = true;
                result = true;
            } else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                isInputValid = true;
            } else {
                System.out.println("Saisie incorrecte (y/n) attendus");
            }
        }
        return result;
    }

    /**
     * Read an integer between min and max
     *
     * @param scan
     * @param min
     * @param max
     * @return
     */
    public static Integer readInteger(Scanner scan, int min, int max) {
        Integer number = null;
        boolean isInputValid = false;
        do {
            String numberString = scan.next();
            try {
                number = Integer.parseInt(numberString.trim());
                if (number >= min && number <= max) {
                    isInputValid = true;
                } else System.out.println("Veuillez saisir un nombre entre " + min + " et " + max);
            } catch (NumberFormatException e) {
                // Gérer l'erreur si le string n'est pas un nombre
                System.out.println("Ce n'est pas un nombre valide !");
            }
        } while (!isInputValid);
        return number;
    }

    /**
     * Read the input and must match the following regex
     *
     * @param scanner
     * @param regex
     * @param errorMessage
     * @return
     */
    public static String readMatchingRegex(Scanner scanner, String regex, String errorMessage) {
        boolean isInputValid = false;
        String res = "";
        while (!isInputValid) {
            res = scanner.next();
            if (res.matches(regex)) {
                isInputValid = true;
            } else {
                System.out.println(errorMessage);
            }
        }
        return res;
    }

    /**
     * Get a non empty Line of input
     *
     * @param scanner
     * @return
     */
    public static String readNonEmptyLine(Scanner scanner) {
        String res = scanner.nextLine(); // empty previous scanner.next that leave a \n
        while (res.isEmpty()) {
            res = scanner.nextLine();
            if (res.isEmpty()) {
                System.out.println("La chaine ne peux pas etre vide");
            }
        }
        return res;
    }

    /**
     * Get a valid input of money
     *
     * @param scanner
     * @return
     */
    public static BigDecimal readMoney(Scanner scanner) {
        BigDecimal res = BigDecimal.ZERO;
        boolean isInputValid = false;
        while (!isInputValid) {
            String str = scanner.next();
            if (Validator.isAValidDecimal(str)) {
                try {
                    res = new BigDecimal(str);
                    isInputValid = true;
                } catch (Exception e) {
                    System.out.println("Erreur lors de la convertion");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid montant ex: 12.4");
            }
        }
        return res;
    }
}
