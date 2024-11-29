/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import db.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GorraDAO {

    // Inserta una nueva gorra en la base de datos
    public void insertarGorra(Gorra gorra) throws SQLException {
        String sql = "INSERT INTO gorras (nombre, precio, descripcion, cantidad) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, gorra.getNombre());
            stmt.setDouble(2, gorra.getPrecio());
            stmt.setString(3, gorra.getDescripcion());
            stmt.setInt(4, gorra.getCantidad());
            stmt.executeUpdate();
        }
    }

    // Obtiene una lista de todas las gorras
    public List<Gorra> obtenerGorras() throws SQLException {
        List<Gorra> lista = new ArrayList<>();
        String sql = "SELECT * FROM gorras";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Gorra gorra = new Gorra();
                gorra.setId(rs.getInt("id"));
                gorra.setNombre(rs.getString("nombre"));
                gorra.setPrecio(rs.getDouble("precio"));
                gorra.setDescripcion(rs.getString("descripcion"));
                gorra.setCantidad(rs.getInt("cantidad"));
                lista.add(gorra);
            }
        }
        return lista;
    }

    // Obtiene una gorra específica por su ID
    public Gorra obtenerGorraPorId(int id) throws SQLException {
        String sql = "SELECT * FROM gorras WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Gorra gorra = new Gorra();
                    gorra.setId(rs.getInt("id"));
                    gorra.setNombre(rs.getString("nombre"));
                    gorra.setPrecio(rs.getDouble("precio"));
                    gorra.setDescripcion(rs.getString("descripcion"));
                    gorra.setCantidad(rs.getInt("cantidad"));
                    return gorra;
                }
            }
        }
        return null; // Devuelve null si no encuentra la gorra
    }

    // Actualiza una gorra existente
    public void actualizarGorra(Gorra gorra) throws SQLException {
        String sql = "UPDATE gorras SET nombre = ?, precio = ?, descripcion = ?, cantidad = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, gorra.getNombre());
            stmt.setDouble(2, gorra.getPrecio());
            stmt.setString(3, gorra.getDescripcion());
            stmt.setInt(4, gorra.getCantidad());
            stmt.setInt(5, gorra.getId());
            stmt.executeUpdate();
        }
    }

    // Elimina una gorra por su ID
    public void eliminarGorra(int id) throws SQLException {
        String sql = "DELETE FROM gorras WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}