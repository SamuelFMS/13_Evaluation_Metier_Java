package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Generic class for all the dao
 *
 * @param <T>
 */
public interface Dao<T> {
    /**
     * Default request to get all the Elements T
     *
     * @param connection
     * @param preparedStatement
     * @return
     */
    default List<T> selectAll(Connection connection, PreparedStatement preparedStatement) {
        List<T> resultList = new ArrayList<>();
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                resultList.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    /**
     * Default request to get all the Elements T but with a statement no prepared
     *
     * @param connection
     * @param sqlRequest
     * @return
     */
    default List<T> selectAll(Connection connection, String sqlRequest) {
        List<T> resultList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sqlRequest)) {
                while (resultSet.next()) {
                    resultList.add(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    /**
     * Convert the ResultSet to the object corresponding
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    T mapRow(ResultSet rs) throws SQLException;
}
