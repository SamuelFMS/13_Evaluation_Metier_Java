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
    User user;

    public Client(Integer idClient, String lastName, String firstName, String email, String address, String numberPhone, String numberPhonePrefix, User user) {
        this.idClient = idClient;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.address = address;
        this.numberPhone = numberPhone;
        this.numberPhonePrefix = numberPhonePrefix;
        this.user = user;
    }

    public List<Order> getOrders(){
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", numberPhone='" + numberPhone + '\'' +
                ", numberPhonePrefix='" + numberPhonePrefix + '\'' +
                ", user=" + user +
                '}';
    }
}
