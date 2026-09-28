package models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Client {
    Integer idClient;
    String lastName;
    String firstName;
    String email;
    String address;
    String numberPhone;
    String numberPhonePrefix;

    public Client(Integer idClient, String lastName, String firstName, String email, String address, String numberPhone, String numberPhonePrefix) {
        this.idClient = idClient;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.address = address;
        this.numberPhone = numberPhone;
        this.numberPhonePrefix = numberPhonePrefix;
    }

    public List<Order> getOrders(){
        return new ArrayList<>();
    }
}
