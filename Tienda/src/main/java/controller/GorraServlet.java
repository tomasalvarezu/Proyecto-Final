/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import model.GorraDAO;
import model.Gorra;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GorraServlet extends HttpServlet {
    private GorraDAO gorraDAO;

    public void init() {
        gorraDAO = new GorraDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idGorra = Integer.parseInt(request.getParameter("id"));
        int cantidadComprada = Integer.parseInt(request.getParameter("cantidad"));

        try {
            Gorra gorra = gorraDAO.obtenerGorraPorId(idGorra);
            if (gorra == null) {
                response.getWriter().write("Error: Gorra no encontrada.");
                return;
            }

            if (cantidadComprada > gorra.getCantidad()) {
                response.getWriter().write("Error: No hay suficiente inventario para esta compra.");
                return;
            }

            // Actualizar la cantidad en la base de datos
            gorra.setCantidad(gorra.getCantidad() - cantidadComprada);
            gorraDAO.actualizarGorra(gorra);

            response.getWriter().write("Compra realizada con éxito. Quedan " + gorra.getCantidad() + " unidades en inventario.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Error al procesar la compra: " + e.getMessage());
        }
    }
}