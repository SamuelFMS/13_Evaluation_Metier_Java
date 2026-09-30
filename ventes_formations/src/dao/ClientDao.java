package dao;

import models.Client;
import models.User;

import java.util.List;

public interface ClientDao {
    /**
     * Return the list of client that belong to the user
     *
     * @param user
     * @return
     */
    List<Client> getClientsForUser(User user);

    /**
     * Create a client
     *
     * @param client
     * @return
     */
    Client createClient(Client client);
}
