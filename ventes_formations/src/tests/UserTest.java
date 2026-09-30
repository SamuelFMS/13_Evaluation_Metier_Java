package tests;

import business.UserBusiness;
import config.DataBaseConfig;
import models.Client;
import models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class UserTest {
    private final UserBusiness userBusiness = new UserBusiness();

    @Before
    public void init() {
        DataBaseConfig.setConnetion("jdbc:mariadb://localhost:3306/test_vente_de_formation", "root", null);
    }

    @Test
    public void testFakeHash() {
        assert Objects.equals(userBusiness.fakeHash("test"), "117102116117");
    }

    @Test
    public void testRegisterUser() {
        String username = "test_" + System.currentTimeMillis();
        assertDoesNotThrow(() -> userBusiness.registerUser(username, "test"));
    }

    @Test
    public void testLoginUser() {
        User user = userBusiness.tryLogin("Samuel", "test");
        assert user != null;
        user = userBusiness.tryLogin("Samuel", "testt");
        assert user == null;
    }

    @Test
    public void testCreateClient() {
        Client client = new Client(null, "test", "test", "test@gamail.com", "15 rue de jsp", "045658569545", "33", new User(1, null));
        userBusiness.createClient(client);
    }

    @Test
    public void testGetClients() {
        List<Client> clients = userBusiness.getClients(new User(1, null));
        assert clients != null;
        assert !clients.isEmpty();
    }
}
