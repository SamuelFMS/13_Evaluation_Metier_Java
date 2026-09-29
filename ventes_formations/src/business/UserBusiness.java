package business;

import dao.ClientDao;
import dao.ClientDaoImpl;
import dao.UserDaoImpl;
import exception.InvalidPhoneNumberException;
import exception.InvalidPhonePrefixException;
import exception.InvalidUsernameException;
import exception.UsernameAlreadyExistException;
import models.Client;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserBusiness {
    private static final UserDaoImpl userDao = new UserDaoImpl();
    private static final ClientDaoImpl clientDao = new ClientDaoImpl();
    public String fakeHash(String password){
        StringBuilder hash = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            hash.append(password.charAt(i)+1);
        }
        return hash.toString();
    }

    public void registerUser(String username, String password){
        // Check if username exist
        if (userDao.pseudoAlreadyExist(username)) {
            throw new UsernameAlreadyExistException();
        }

        // Check if username is at least 3 len
        if(username.length() < 3) {
            throw new InvalidUsernameException();
        }

        userDao.register(username, fakeHash(password));
    }

    public User tryLogin(String username, String password){
        return userDao.login(username, fakeHash(password));
    }

    public Client createClient(Client client){
        if(client.getUser() == null){
            throw new InvalidUsernameException();
        }
        if(client.getNumberPhonePrefix().length() > 3){
            throw new InvalidPhonePrefixException("The number phone prefix is too long");
        }
        if(client.getNumberPhone().length() > 20){
            throw new InvalidPhoneNumberException("The number phone is too long");
        }
        clientDao.createClient(client);
        return client;
    }

    public List<Client> getClients(User user){
        if(user == null){
            throw new InvalidUsernameException();
        }
        List<Client> clients = clientDao.getClientsForUser(user);
        clients.forEach(client -> client.setUser(user));
        return clients;
    }
}
