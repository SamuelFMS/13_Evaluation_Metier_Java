package business;

import dao.UserDaoImpl;
import exception.InvalidUsernameException;
import exception.UsernameAlreadyExistException;
import models.User;

public class UserBusiness {
    private static final UserDaoImpl userDao = new UserDaoImpl();

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
}
