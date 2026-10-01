package models;

import lombok.Getter;
import lombok.Setter;
import utils.TableRow;

/**
 * Model Client contain all the informations of the Client
 */
@Getter
@Setter
public class Client implements TableRow {
    /**
     * Id of the client in the database
     */
    Integer idClient;
    /**
     * Last name of the client
     */
    String lastName;
    /**
     * First name of the cient
     */
    String firstName;
    /**
     * Email of the client
     */
    String email;
    /**
     * Address of the client
     */
    String address;
    /**
     * Number phone of the client
     */
    String numberPhone;
    /**
     * Number phone prefix of the client
     */
    String numberPhonePrefix;
    /**
     * User assign to the client
     */
    User user;

    /**
     * Default constructor
     *
     * @param idClient
     * @param lastName
     * @param firstName
     * @param email
     * @param address
     * @param numberPhone
     * @param numberPhonePrefix
     * @param user
     */
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

    @Override
    public String[] getColumnNames() {
        return new String[]{"id", "prénom", "nom", "email"};
    }

    @Override
    public String[] getRowData() {
        return new String[]{String.valueOf(idClient), firstName, lastName, email};
    }
}
