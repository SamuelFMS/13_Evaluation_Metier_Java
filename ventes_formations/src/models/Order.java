package models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {
    /**
     * Id of the order
     */
    Integer idOrder;
    /**
     * Date of the order
     */
    LocalDate orderDate;
    /**
     * User that made the order
     */
    User user;
    /**
     * Client that was assign to the order
     */
    Client client;
    /**
     * What formation it contains at what price he bought the formation
     */
    List<Contain> contains;

    /**
     * Default constructor
     *
     * @param idOrder
     * @param orderDate
     * @param user
     * @param client
     */
    public Order(Integer idOrder, LocalDate orderDate, User user, Client client) {
        this.idOrder = idOrder;
        this.orderDate = orderDate;
        this.user = user;
        this.client = client;
        this.contains = new ArrayList<>();
    }

    /**
     * Add a contain to the list contains
     *
     * @param contain
     */
    public void addContain(Contain contain) {
        this.contains.add(contain);
    }
}
