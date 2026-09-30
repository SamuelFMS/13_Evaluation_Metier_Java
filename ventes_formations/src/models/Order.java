package models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Order {
    Integer idOrder;
    LocalDate orderDate;
    User user;
    Client client;
    List<Contain> contains;

    public Order(Integer idOrder, LocalDate orderDate, User user, Client client) {
        this.idOrder = idOrder;
        this.orderDate = orderDate;
        this.user = user;
        this.client = client;
        this.contains = new ArrayList<>();
    }

    public BigDecimal getTotalPrice(){
        return BigDecimal.ZERO;
    }

    public void addContain(Contain contain) {
        this.contains.add(contain);
    }
}
