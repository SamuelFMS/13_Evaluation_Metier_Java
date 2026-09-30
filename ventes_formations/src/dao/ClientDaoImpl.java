package dao;

import config.DataBaseConfig;
import models.Client;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClientDaoImpl implements ClientDao, Dao<Client> {
    @Override
    public Client createClient(Client client) {
        String sql = "INSERT INTO client(last_name, first_name, email, address, number_phone, number_phone_prefix, id_user) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DataBaseConfig.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, client.getLastName());
                preparedStatement.setString(2, client.getFirstName());
                preparedStatement.setString(3, client.getEmail());
                preparedStatement.setString(4, client.getAddress());
                preparedStatement.setString(5, client.getNumberPhone());
                preparedStatement.setString(6, client.getNumberPhonePrefix());
                preparedStatement.setInt(7, client.getUser().getIdUser());
                preparedStatement.executeUpdate();

                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        client.setIdClient(id);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    @Override
    public List<Client> getClientsForUser(User user) {
        String sql = "SELECT * FROM client WHERE id_user = ?";
        try (Connection connection = DataBaseConfig.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, user.getIdUser());
                return selectAll(connection, preparedStatement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client mapRow(ResultSet rs) throws SQLException {
        return new Client(rs.getInt("id_client"), rs.getString("last_name"), rs.getString("first_name"), rs.getString("email"), rs.getString("address"), rs.getString("number_phone"), rs.getString("number_phone_prefix"), null);
    }
}
