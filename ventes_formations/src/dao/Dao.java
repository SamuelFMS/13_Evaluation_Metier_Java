package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface Dao<T> {
    default List<T> selectAll(Connection connection, PreparedStatement preparedStatement){
        List<T> resultList = new ArrayList<>();
        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()){
                resultList.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    default List<T> selectAll(Connection connection, String sqlRequest){
        List<T> resultList = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
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

    abstract T mapRow(ResultSet rs) throws SQLException;
}
