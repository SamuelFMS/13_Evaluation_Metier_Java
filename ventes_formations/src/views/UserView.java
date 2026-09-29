package views;

import business.FormationBusiness;
import exception.EmptyArrayException;
import models.Formation;
import models.FormationItemBasket;
import models.User;
import utils.InputUtils;
import utils.SearchTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserView {
    private final List<Formation> basket;
    private User userSession;

    public UserView(User userSession) {
        if (userSession.getLogin() == null) {
            throw new NullPointerException("Login is null");
        }
        this.userSession = userSession;
        basket = new ArrayList<>();
    }


    public void addToBasket(Scanner scanner, FormationBusiness formationBusiness) {
        List<Formation> availableFormation = formationBusiness.getAllAvailableFormation();
        boolean displayBasket = true;
        while (displayBasket) {
            List<FormationItemBasket> availableFormationItem = availableFormation.stream().map(formation -> new FormationItemBasket(basket.stream().anyMatch(formationDeux -> formation.getTitleFormation().equals(formationDeux.getTitleFormation())), formation)).collect(Collectors.toList());
            SearchTable<FormationItemBasket> searchTable = new SearchTable<>(availableFormationItem);
            try {
                String idFormationString = searchTable.show(scanner, "Quel Formation souhaitez vous ajoutez ou supprimer");
                if (idFormationString == null) {
                    displayBasket = false;
                } else {
                    int idFormation = Integer.parseInt(idFormationString);
                    if(basket.stream().anyMatch(formationDeux -> formationDeux.getIdFormation() == idFormation)) {
                        basket.stream().filter(formation -> formation.getIdFormation() == idFormation).findFirst().ifPresent(basket::remove);
                    } else {
                        availableFormation.stream().filter(formation -> formation.getIdFormation() == idFormation).findFirst().ifPresent(basket::add);
                    }
                }
            } catch (EmptyArrayException e) {
                System.out.println("Aucune formation est disponnible");
            }
        }
    }

    public void show(Scanner scanner, FormationBusiness formationBusiness) {
        System.out.println("Bonjour " + userSession.getLogin());
        while (userSession != null) {
            System.out.println("1- Ajouter une formation au panier");
            System.out.println("2- Retirer une formation au panier");
            System.out.println("3- Consulter le panier");
            System.out.println("4- Passer commande");
            System.out.println("0- Se deconnecter");
            switch (InputUtils.readInteger(scanner, 0, 4)) {
                case 0:
                    userSession = null;
                    break;
                case 1:
                    addToBasket(scanner, formationBusiness);
                    break;


            }
        }
    }
}
