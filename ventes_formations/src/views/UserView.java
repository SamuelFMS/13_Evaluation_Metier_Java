package views;

import business.FormationBusiness;
import business.UserBusiness;
import exception.EmptyArrayException;
import models.Client;
import models.Formation;
import models.FormationItemBasket;
import models.User;
import utils.InputUtils;
import utils.SearchTable;

import java.math.BigDecimal;
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

    public Client createClient(Scanner scanner, UserBusiness userBusiness){
        System.out.println("Entrez le prénom");
        String firstName = scanner.next();
        System.out.println("Entrez le nom de famille");
        String lastName = scanner.next();
        System.out.println("Entrez le email");
        String email = scanner.next();
        System.out.println("Entrez address");
        String address = InputUtils.readNonEmptyLine(scanner);
        String phonePrefix = "33";
        System.out.println("Entrez le numéro de téléphone");
        String phone = scanner.next();
        Client client = new Client(null, lastName, firstName,email, address, phone, phonePrefix, userSession);
        return userBusiness.createClient(client);
    }

    public Client getClient(Scanner scanner,  UserBusiness userBusiness){
        List<Client> clients = userBusiness.getClients(userSession);
        clients.forEach(System.out::println);

        Client client = createClient(scanner, userBusiness);
        return client;
    }

    public void payBasket(Scanner scanner, UserBusiness userBusiness){
        System.out.println("Récapitulatif: ");
        for (Formation formation : basket) {
            System.out.println(formation);
        }
        System.out.println("Prix totale: " +     basket.stream()
                .map(Formation::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        getClient(scanner,userBusiness);

    }

    public void show(Scanner scanner, FormationBusiness formationBusiness, UserBusiness userBusiness) {
        System.out.println("Bonjour " + userSession.getLogin());
        while (userSession != null) {
            System.out.println("1- Gérer mon panier de formation");
            System.out.println("2- Passer commande");
            System.out.println("0- Se deconnecter");
            switch (InputUtils.readInteger(scanner, 0, 2)) {
                case 0:
                    userSession = null;
                    break;
                case 1:
                    addToBasket(scanner, formationBusiness);
                    break;
                case 2:
                    payBasket(scanner, userBusiness);
                    break;
                default:
                    System.out.println("default");
                    break;

            }
        }
    }
}
