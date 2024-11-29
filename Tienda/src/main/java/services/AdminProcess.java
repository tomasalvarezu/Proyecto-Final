/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import db.DatabaseConnection;
import model.Gorra;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminProcess {

    public List<Gorra> obtenerInventario() {
        List<Gorra> inventario = new ArrayList<>();
        String sqlSelect = "SELECT * FROM gorras";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sqlSelect);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Gorra gorra = new Gorra(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getString("descripcion"),
                        rs.getInt("cantidad")
                );
                inventario.add(gorra);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el inventario.");
            e.printStackTrace();
        }

        return inventario;
    }

    public void guardarGorra(Gorra nuevaGorra) {
        String sqlInsert = "INSERT INTO gorras (nombre, precio, descripcion, cantidad) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sqlInsert)) {
            pstmt.setString(1, nuevaGorra.getNombre());
            pstmt.setDouble(2, nuevaGorra.getPrecio());
            pstmt.setString(3, nuevaGorra.getDescripcion());
            pstmt.setInt(4, nuevaGorra.getCantidad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
        }
    }

    public void eliminarGorra(int id) {
        String sqlDelete = "DELETE FROM gorras WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sqlDelete)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar la gorra.");
            e.printStackTrace();
        }
    }

    public void actualizarGorra(int id, String nombre, double precio, String descripcion, int cantidad) {
        String sqlUpdate = "UPDATE gorras SET nombre = ?, precio = ?, descripcion = ?, cantidad = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sqlUpdate)) {
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, precio);
            pstmt.setString(3, descripcion);
            pstmt.setInt(4, cantidad);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar la gorra.");
            e.printStackTrace();
        }
    }
}

