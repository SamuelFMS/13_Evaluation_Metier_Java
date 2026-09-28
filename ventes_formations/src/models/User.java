package models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class User {
    private int idUser;
    private String login;

    public User(int idUser, String login) {
        this.idUser = idUser;
        this.login = login;
    }

    public List<Order> getOrders() {
        return new ArrayList<>();
    }
}
