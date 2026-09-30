package dao;

import models.Contain;

import java.sql.Connection;
import java.sql.SQLException;

public interface ContainDao {
    /**
     * Create a contain in the database
     *
     * @param connection
     * @param contain
     * @return
     * @throws SQLException
     */
    boolean createContain(Connection connection, Contain contain) throws SQLException;
}
