package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    /**
     * Id of the user in the database or null if not
     */
    private int idUser;
    /**
     * Login of the user
     */
    private String login;

    /**
     * Default constructor
     *
     * @param idUser
     * @param login
     */
    public User(int idUser, String login) {
        this.idUser = idUser;
        this.login = login;
    }
}
