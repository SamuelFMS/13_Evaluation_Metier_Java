package utils;

import validation.Validator;

import java.math.BigDecimal;
import java.util.Scanner;

public interface InputUtils {
    static boolean readBoolean(Scanner scanner){
        boolean result = false;
        boolean isInputValid = false;
        while (!isInputValid) {
            String input = scanner.next();
            if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")){
                isInputValid = true;
                result = true;
            }
            else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                isInputValid = true;
            }
            else{
                System.out.println("Saisie incorrecte (y/n) attendus");
            }
        }
        return result;
    }

    static Integer readInteger(Scanner scan, int min, int max) {
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

    static String readMatchingRegex(Scanner scanner, String regex, String errorMessage){
        boolean isInputValid = false;
        String res = "";
        while(!isInputValid){
            res = scanner.next();
            if(res.matches(regex)){
                isInputValid = true;
            } else {
                System.out.println(errorMessage);
            }
        }
        return res;
    }

    static String readNonEmptyLine(Scanner scanner) {
        String res = scanner.nextLine();
        while (res.isEmpty()) {
            res = scanner.nextLine();
            if(res.isEmpty()) {
                System.out.println("La chaine ne peux pas etre vide");
            }
        }
        return res;
    }

    static BigDecimal readMoney(Scanner scanner) {
        BigDecimal res = BigDecimal.ZERO;
        boolean isInputValid = false;
        while(!isInputValid) {
            String str = scanner.next();
            if(Validator.isAValidDecimal(str)) {
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
