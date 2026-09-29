package views;

import business.FormationBusiness;
import exception.EmptyArrayException;
import models.Formation;
import utils.Actions;
import utils.DisplayTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GuestView {
    private GuestView() {
        /* This utility class should not be instantiated */
    }

    /**
     * Display all the formations that is in
     *
     * @param dt
     * @param listOfAvailableFormations
     */
    public static void displayDistanciel(DisplayTable<Formation> dt, List<Formation> listOfAvailableFormations) {
        dt.update(listOfAvailableFormations.stream().filter(Formation::isRemote).collect(Collectors.toList()));
        dt.setActions(new ArrayList<>());
    }

    public static void displayPresentiel(DisplayTable<Formation> dt, List<Formation> listOfAvailableFormations) {
        dt.update(listOfAvailableFormations.stream().filter(formation -> !formation.isRemote()).collect(Collectors.toList()));
        dt.setActions(new ArrayList<>());
    }

    public static void displayFormations(Scanner scanner, FormationBusiness formationBusiness) {
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

    public static void show(Scanner scanner, FormationBusiness formationBusiness) {
        System.out.println("1- Afficher les formations");
        System.out.println("2- Creer un compte");
        System.out.println("3- Se connecter a un compte");
        displayFormations(scanner, formationBusiness);
    }
}
