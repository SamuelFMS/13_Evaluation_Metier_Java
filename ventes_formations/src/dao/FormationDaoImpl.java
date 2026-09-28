package dao;

import config.DataBaseConfig;
import models.Formation;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class FormationDaoImpl implements FormationDao, Dao<Formation> {
    @Override
    public List<Formation> findAllAvailable() {
        String sql = "SELECT * FROM formation WHERE is_available = true";
        try (Connection connection = DataBaseConfig.getConnection()){
            return selectAll(connection, sql);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Formation mapRow(ResultSet rs) throws SQLException {
        return new Formation(rs.getInt("id_formation"), rs.getString("title_formation"), rs.getString("description"), rs.getInt("number_of_days"), rs.getBoolean("is_remote"), rs.getBigDecimal("price"), rs.getBoolean("is_available"));
    }
}
