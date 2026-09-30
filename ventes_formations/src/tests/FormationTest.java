package tests;

import business.FormationBusiness;
import business.UserBusiness;
import config.DataBaseConfig;
import models.Client;
import models.Formation;
import models.Order;
import models.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class FormationTest {
    private final FormationBusiness formationBusiness = new FormationBusiness();
    private final UserBusiness userBusiness = new UserBusiness();

    @Before
    public void init() {
        DataBaseConfig.setConnetion("jdbc:mariadb://localhost:3306/test_vente_de_formation", "root", null);
    }

    @Test
    public void testGetAllAvailableFormation() {
        List<Formation> formations = formationBusiness.getAllAvailableFormation();
        assert formations != null;
        assert !formations.isEmpty();
    }

    @Test
    public void testOrderFormation() {
        User user = new User(1, null);
        Client client = new Client(1, null, null, null, null, null, null, null);
        Order order = new Order(null, LocalDate.now(), user, client);
        assert formationBusiness.orderFormation(order);
        assert order.getIdOrder() != null;
    }
}
