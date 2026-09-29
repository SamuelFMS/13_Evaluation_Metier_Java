package dao;

import models.User;

public interface UserDao {
    /**
     * Check if a pseudonym is already exist return true if the case
     * @return
     */
    boolean pseudoAlreadyExist(String pseudo);

    /**
     * Register a user to the database return true if success return false otherwise
     * @param username
     * @param password
     * @return
     */
    boolean register(String username, String password);

    /**
     * login a user
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);
}
