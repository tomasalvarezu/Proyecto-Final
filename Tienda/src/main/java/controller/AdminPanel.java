package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */



import com.google.gson.Gson;
import model.Gorra;
import services.AdminProcess;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


public class AdminPanel extends HttpServlet {

    private final AdminProcess adminProcess = new AdminProcess();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("getInventario".equalsIgnoreCase(action)) {
            // Obtener el inventario y convertirlo a JSON
            List<Gorra> inventario = adminProcess.obtenerInventario();
            String json = new Gson().toJson(inventario);

            response.setContentType("application/json");
            response.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equalsIgnoreCase(action)) {
            // Agregar nueva gorra
            String nombre = request.getParameter("nombre");
            double precio = Double.parseDouble(request.getParameter("precio"));
            String descripcion = request.getParameter("descripcion");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));

            Gorra nuevaGorra = new Gorra(0, nombre, precio, descripcion, cantidad);
            adminProcess.guardarGorra(nuevaGorra);

            response.getWriter().write("success");

        } else if ("delete".equalsIgnoreCase(action)) {
            // Eliminar gorra por ID
            int id = Integer.parseInt(request.getParameter("id"));
            adminProcess.eliminarGorra(id);

            response.getWriter().write("success");
        }
    }
}
