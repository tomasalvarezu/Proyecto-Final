package db;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author maxto
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/tienda_gorras";
    private static final String USER = "root";
    private static final String PASSWORD = "500423gt";

    public static Connection getConnection() throws SQLException {
        try {
            // Cargar el driver (opcional si ya está en el classpath)
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MariaDB no encontrado.");
            throw new SQLException("Driver no encontrado.", e);
        }

        // Conectar a la base de datos
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
