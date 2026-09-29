import business.FormationBusiness;
import lombok.Getter;
import views.GuestView;

import java.util.Scanner;

@Getter
public class Main {

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            FormationBusiness formationBusiness = new FormationBusiness();
            GuestView.show(scanner, formationBusiness);
        }
    }
}