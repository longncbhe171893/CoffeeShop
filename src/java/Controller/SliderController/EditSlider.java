/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.SliderController;

import DAO.BlogDao;
import DAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

public class EditSlider extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        try {
        response.setContentType("text/html;charset=UTF-8");
        String title = request.getParameter("title");
        String img = request.getParameter("img");
        String id  = request.getParameter("id");
        String status = request.getParameter("status");
        
        ProductDAO listP = new ProductDAO();
        listP.updateSlider(title, img, id, status);
        response.sendRedirect("ManageSlider");
//        } catch (Exception e) {
//            response.sendRedirect("./404.html");
//        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
