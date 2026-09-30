package config;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class DataBaseConfig {
    /**
     * URL of the database
     */
    private static String url = "jdbc:mariadb://localhost:3306/vente_de_formation";

    /**
     * User of the database
     */
    private static String user = "vente_de_formation";
    /**
     * Password of the database
     */
    private static String password = "fms2026";

    private DataBaseConfig() {
        /* This utility class should not be instantiated */
    }

    /**
     * Change the database connection url, user and password
     *
     * @param url
     * @param user
     * @param password
     */
    public static void setConnetion(String url, String user, String password) {
        DataBaseConfig.url = url;
        DataBaseConfig.user = user;
        DataBaseConfig.password = password;
    }

    /**
     * Get a connection of the database
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DataBaseConfig.url, DataBaseConfig.user, DataBaseConfig.password);
    }
}