package dao;

import models.Contain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContainDaoImpl implements ContainDao, Dao<Contain> {

    public boolean createContain(Connection connection, Contain contain) throws SQLException {
        String sql = "INSERT INTO contain(id_formation, id_order, unit_price) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, contain.getFormation().getIdFormation());
            preparedStatement.setInt(2, contain.getOrder().getIdOrder());
            preparedStatement.setBigDecimal(3, contain.getUnitPrice());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public Contain mapRow(ResultSet rs) throws SQLException {
        return new Contain(null, null, rs.getBigDecimal("unit_price"));
    }
}
