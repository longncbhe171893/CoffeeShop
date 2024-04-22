/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * 
 */
public class AddUser extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to doPost when GET request
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get information from request
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String sex = request.getParameter("sex");
            String userpointStr = request.getParameter("userpoint");
            double userpoint = 0.0; // Default value if not provided
            if (userpointStr != null && !userpointStr.isEmpty()) {
                userpoint = Double.parseDouble(userpointStr);
            }
            
            // Create UserDAO instance
            UserDAO udao = new UserDAO();
               // udao.addUser(name, email, password, address, phone, sex, 0);
                response.sendRedirect("ManagerUser");
        } catch (Exception e) {
            // Handle other exceptions and display error message
            response.getWriter().println("An error occurred while adding user: " + e.getMessage());
        }
    }
}