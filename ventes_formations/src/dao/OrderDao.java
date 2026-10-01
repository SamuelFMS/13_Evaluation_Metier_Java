package dao;

import models.Order;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface of table order sql request
 */
public interface OrderDao {
    /**
     * Create an order in the database
     *
     * @param order
     * @return
     */
    boolean createOrder(Connection connection, Order order) throws SQLException;
}
