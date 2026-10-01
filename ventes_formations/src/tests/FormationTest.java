package tests;

import business.FormationBusiness;
import config.DataBaseConfig;
import models.Client;
import models.Contain;
import models.Formation;
import models.Order;
import models.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Test Class for the Training Business
 */
public class FormationTest {
    /**
     * Business training courses to try out
     */
    private final FormationBusiness formationBusiness = new FormationBusiness();

    /**
     * Before test execution
     */
    @Before
    public void init() {
        DataBaseConfig.setConnetion("jdbc:mariadb://localhost:3306/test_vente_de_formation", "root", null);
    }

    /**
     * Test of method get all available formation
     */
    @Test
    public void testGetAllAvailableFormation() {
        List<Formation> formations = formationBusiness.getAllAvailableFormation();
        assert formations != null;
        assert !formations.isEmpty();
    }

    /**
     * Test of method order a formation
     */
    @Test
    public void testOrderFormation() {
        User user = new User(1, null);
        Client client = new Client(1, null, null, null, null, null, null, null);
        Order order = new Order(null, LocalDate.now(), user, client);
        Formation formation = formationBusiness.getAllAvailableFormation().get(0);
        Contain contain = new Contain(order, formation, formation.getPrice());
        order.addContain(contain);
        assertDoesNotThrow(() -> assertTrue(formationBusiness.orderFormation(order)));
        assert order.getIdOrder() != null;
    }
}
