package dao;

import models.Order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderDaoImpl implements OrderDao, Dao<Order> {

    @Override
    public Order mapRow(ResultSet rs) throws SQLException {
        return new Order(
                rs.getInt("id_order"),
                rs.getDate("date_commande").toLocalDate(),
                null,
                null
        );
    }

    @Override
    public boolean createOrder(Connection connection, Order order) throws SQLException {
        String sql = "INSERT INTO order_(date_commande, id_user, id_client) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setDate(1, Date.valueOf(order.getOrderDate()));
            preparedStatement.setInt(2, order.getUser().getIdUser());
            preparedStatement.setInt(3, order.getClient().getIdClient());
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    order.setIdOrder(id);
                    return true;
                }
            }
        }
        return false;
    }
}