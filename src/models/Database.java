package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/db_foot?serverTimezone=UTC";
    private static final String USER = "root";  // Remplace par ton utilisateur MySQL
    private static final String PASSWORD = "";  // Mets ton mot de passe MySQL

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Charge le driver MySQL
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL non trouvé", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
