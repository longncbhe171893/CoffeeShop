/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Hoàng Vũ
 */
public class UserDetails extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        try {
        response.setContentType("text/html;charset=UTF-8");
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
              int id = Integer.valueOf(request.getParameter("id"));
        UserDAO udao = new UserDAO();
        udao.updateUser(name, email, password, address, phone, sex, userpoint, id);
            response.sendRedirect("ManagerUser");
//        } catch (Exception e) {
//            response.sendRedirect("./404.html");
//        }
    }
     @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    }

   
