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

    public Order(Integer idOrder, LocalDate orderDate, User user, Client client) {
        this.idOrder = idOrder;
        this.orderDate = orderDate;
        this.user = user;
        this.client = client;
    }

    public List<Formation> getFormations(){
        return new ArrayList<>();
    }

    public BigDecimal getTotalPrice(){
        return BigDecimal.ZERO;
    }

    public boolean addFormation(Formation formation) {
        return false;
    }

    public boolean removeFormation(Formation formation) {
        return false;
    }
}
