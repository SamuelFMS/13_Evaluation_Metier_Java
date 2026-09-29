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

public class UserDaoImpl implements UserDao, Dao<User> {

    @Override
    public boolean pseudoAlreadyExist(String pseudo) {
        String sql = "SELECT * FROM user_ WHERE login = ?";
        try (Connection connection = DataBaseConfig.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, pseudo);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean register(String username, String hashed_password) {
        String sql = "INSERT INTO user_(login, hashed_password) VALUES (?, ?)";
        try (Connection connection = DataBaseConfig.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, hashed_password);
                return preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User login(String username, String hashed_password) {
        String sql = "SELECT * FROM user_ WHERE login = ? AND hashed_password = ?";
        try(Connection connection = DataBaseConfig.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, hashed_password);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User mapRow(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id_user"),rs.getString("login"));
    }
}
