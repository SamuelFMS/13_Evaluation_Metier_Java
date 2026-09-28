package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfig {
    private DataBaseConfig() {
        /* This utility class should not be instantiated */
    }

    public static final String URL = "jdbc:mariadb://localhost:3306/vente_de_formation";
    public static final String USER = "vente_de_formation";
    public static final String PASSWORD = "fms2026";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USER, DataBaseConfig.PASSWORD);
    }
}