package business;

import config.DataBaseConfig;
import dao.ContainDao;
import dao.ContainDaoImpl;
import dao.FormationDaoImpl;
import dao.OrderDaoImpl;
import models.Contain;
import models.Formation;
import models.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FormationBusiness {
    private static final FormationDaoImpl formationDao = new FormationDaoImpl();
    private static final OrderDaoImpl orderDao = new OrderDaoImpl();
    private static final ContainDaoImpl containDao = new ContainDaoImpl();

    public List<Formation> getAllAvailableFormation(){
        return formationDao.findAllAvailable();
    }

    public boolean orderFormation(Order order) {
        try(Connection connection = DataBaseConfig.getConnection()){
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
