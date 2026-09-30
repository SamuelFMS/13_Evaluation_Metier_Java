package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfig {
    /**
     * URL of the database
     */
    public static final String URL = "jdbc:mariadb://localhost:3306/vente_de_formation";
    /**
     * User of the database
     */
    public static final String USER = "vente_de_formation";
    /**
     * Password of the database
     */
    public static final String PASSWORD = "fms2026";

    private DataBaseConfig() {
        /* This utility class should not be instantiated */
    }

    /**
     * Get a connection of the database
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USER, DataBaseConfig.PASSWORD);
    }
}