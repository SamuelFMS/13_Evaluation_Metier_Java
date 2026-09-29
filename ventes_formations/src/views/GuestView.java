package views;

import business.FormationBusiness;
import business.UserBusiness;
import exception.EmptyArrayException;
import models.Client;
import models.Formation;
import models.User;
import utils.Actions;
import utils.DisplayTable;
import utils.InputUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GuestView {
    public GuestView() {
    }

    /**
     * Display all the formations that is in
     *
     * @param dt
     * @param listOfAvailableFormations
     */
    public void displayDistanciel(DisplayTable<Formation> dt, List<Formation> listOfAvailableFormations) {
        dt.update(listOfAvailableFormations.stream().filter(Formation::isRemote).collect(Collectors.toList()));
        dt.setActions(new ArrayList<>());
    }

    public void displayPresentiel(DisplayTable<Formation> dt, List<Formation> listOfAvailableFormations) {
        dt.update(listOfAvailableFormations.stream().filter(formation -> !formation.isRemote()).collect(Collectors.toList()));
        dt.setActions(new ArrayList<>());
    }

    public void displayFormations(Scanner scanner, FormationBusiness formationBusiness) {
        List<Formation> listOfAvailableFormations = formationBusiness.getAllAvailableFormation();
        DisplayTable<Formation> displayTable = new DisplayTable<>(listOfAvailableFormations);
        ArrayList<Actions> customActions = new ArrayList<>(Arrays.asList(new Actions('D', "À distance", () -> displayDistanciel(displayTable, listOfAvailableFormations)), new Actions('P', "Présentiel", () -> displayPresentiel(displayTable, listOfAvailableFormations))));
        displayTable.setActions(customActions);
        try {
            displayTable.show(scanner, null);
        } catch (EmptyArrayException e) {
            throw new RuntimeException(e);
        }
    }

    public void createAccount(Scanner scanner, UserBusiness userBusiness){
        System.out.println("Veuillez entrez un login");
        String login = scanner.next();
        System.out.println("Veuillez entrez un mot de passe");
        String password = scanner.next();
        userBusiness.registerUser(login, password);
    }

    public void login(Scanner scanner, UserBusiness userBusiness, FormationBusiness formationBusiness){
        System.out.println("Veuillez entrez un login");
        String login = scanner.next();
        System.out.println("Veuillez entrez un mot de passe");
        String password = scanner.next();
        User user = userBusiness.tryLogin(login, password);
        if(user != null){
            UserView userView = new UserView(user);
            userView.show(scanner, formationBusiness);
        }
    }

    public void show(Scanner scanner, FormationBusiness formationBusiness, UserBusiness userBusiness) {
        System.out.println("1- Afficher les formations");
        System.out.println("2- Créer un compte");
        System.out.println("3- Se connecter a un compte");
        System.out.println("0- Stop");
        Integer choice = InputUtils.readInteger(scanner, 1,3);
        switch (choice) {
            case 0:
                System.out.println("Ok");
                break;
            case 1:
                displayFormations(scanner, formationBusiness);
                break;
            case 2:
                createAccount(scanner, userBusiness);
                break;
            case 3:
                login(scanner, userBusiness, formationBusiness);
                break;
            default:
                throw new RuntimeException("Wrong choice");
        }
    }
}
