package views;

import business.FormationBusiness;
import business.UserBusiness;
import exception.EmptyArrayException;
import models.Client;
import models.Contain;
import models.Formation;
import models.FormationItemBasket;
import models.Order;
import models.User;
import utils.Actions;
import utils.InputUtils;
import utils.SearchTable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserView {
    /**
     * current basket
     */
    private final List<Formation> basket;
    /**
     * User log in
     */
    private User userSession;

    /**
     * Constructor
     *
     * @param userSession
     */
    public UserView(User userSession) {
        if (userSession.getLogin() == null) {
            throw new NullPointerException("Login is null");
        }
        this.userSession = userSession;
        basket = new ArrayList<>();
    }

    /**
     * Manage item in the basket add or delete the item of the basket
     *
     * @param scanner
     * @param formationBusiness
     */
    public void manageBasket(Scanner scanner, FormationBusiness formationBusiness) {
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
                    if (basket.stream().anyMatch(formationDeux -> formationDeux.getIdFormation() == idFormation)) {
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

    /**
     * Create a client
     *
     * @param scanner
     * @param userBusiness
     * @return
     */
    public Client createClient(Scanner scanner, UserBusiness userBusiness) {
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
        Client client = new Client(null, lastName, firstName, email, address, phone, phonePrefix, userSession);
        client = userBusiness.createClient(client);
        if (client.getIdClient() == null) {
            throw new RuntimeException("Echec lors de la création du client");
        } else {
            System.out.println("le client a bien été crée");
            return client;
        }
    }

    /**
     * Get a client or make one or cancel
     *
     * @param scanner
     * @param userBusiness
     * @return
     */
    public Client getClient(Scanner scanner, UserBusiness userBusiness) {
        List<Client> clients = userBusiness.getClients(userSession);
        try {
            SearchTable<Client> searchTable = new SearchTable<>(clients);
            List<Actions> actions = new ArrayList<>();
            actions.add(new Actions('C', "Créer un Client", () -> clients.add(createClient(scanner, userBusiness))));
            searchTable.setActions(actions);
            String idClient = searchTable.show(scanner, "Entrez l'id d'un client");
            if (idClient == null) {
                return null;
            } else {
                Optional<Client> client = clients.stream().filter(c -> c.getIdClient() == Integer.parseInt(idClient)).findFirst();

                return client.orElseGet(() -> createClient(scanner, userBusiness));
            }


        } catch (EmptyArrayException e) {
            System.out.println("Aucune client, création d'un client");
            return createClient(scanner, userBusiness);
        }


    }

    /**
     * Pay the basket and assign to a client
     *
     * @param scanner
     * @param userBusiness
     * @param formationBusiness
     */
    public void payBasket(Scanner scanner, UserBusiness userBusiness, FormationBusiness formationBusiness) {
        System.out.println("Récapitulatif: ");
        if(basket.isEmpty()){
            System.out.println("Impossible panier vide");
            return;
        }
        for (Formation formation : basket) {
            System.out.println(formation);
        }
        System.out.println("Prix totale: " + basket.stream().map(Formation::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));

        Client client = getClient(scanner, userBusiness);
        if (client != null) {
            System.out.println("Etes vous sur de vouloir mettre les formations au client " + client.getFirstName() + " (y/n): ");
            if (InputUtils.readBoolean(scanner)) {
                Order order = new Order(null, LocalDate.now(), userSession, client);
                basket.forEach(formation -> order.addContain(new Contain(order, formation, formation.getPrice())));
                try {
                    if (formationBusiness.orderFormation(order)) {
                        System.out.println("Formation enregistrer avec succes");
                    } else {
                        System.out.println("Une erreur c'est produite");
                    }
                } catch (EmptyArrayException e) {
                    System.out.println("Panier vide impossible");
                }
            }
        }
    }

    /**
     * Display the user
     *
     * @param scanner
     * @param formationBusiness
     * @param userBusiness
     */
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
                    manageBasket(scanner, formationBusiness);
                    break;
                case 2:
                    payBasket(scanner, userBusiness, formationBusiness);
                    break;
                default:
                    System.out.println("default");
                    break;

            }
        }
    }
}
