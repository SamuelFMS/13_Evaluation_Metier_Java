package business;

import config.DataBaseConfig;
import dao.ContainDaoImpl;
import dao.FormationDaoImpl;
import dao.OrderDaoImpl;
import exception.EmptyArrayException;
import models.Contain;
import models.Formation;
import models.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FormationBusiness {
    /**
     * Formation DAO
     */
    private static final FormationDaoImpl formationDao = new FormationDaoImpl();
    /**
     * Order dao
     */
    private static final OrderDaoImpl orderDao = new OrderDaoImpl();
    /**
     * Contain dao
     */
    private static final ContainDaoImpl containDao = new ContainDaoImpl();

    /**
     * Find all the formation that are available to order
     *
     * @return
     */
    public List<Formation> getAllAvailableFormation() {
        return formationDao.findAllAvailable();
    }

    /**
     * Order a formation
     *
     * @param order
     * @return
     */
    public boolean orderFormation(Order order) throws EmptyArrayException {
        if(order.getContains().isEmpty()) {
            throw new EmptyArrayException();
        }
        try (Connection connection = DataBaseConfig.getConnection()) {
            connection.setAutoCommit(false);
            try {
                boolean success = true;
                if (orderDao.createOrder(connection, order)) {
                    for (Contain contain : order.getContains()) {
                        if (!containDao.createContain(connection, contain)) {
                            success = false;
                        }
                    }
                } else {
                    success = false;
                }
                if (success) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
                return success;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
