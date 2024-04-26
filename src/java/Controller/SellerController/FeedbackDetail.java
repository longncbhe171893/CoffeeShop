/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.SellerController;

import DAO.OrderDAO;
import Model.OrderDetail;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author anhvu
 */
public class FeedbackDetail extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int userId = Integer.valueOf(request.getParameter("user"));

            int index = Integer.valueOf(request.getParameter("index"));

            int orderId = Integer.valueOf(request.getParameter("orderId"));

            int productId = Integer.valueOf(request.getParameter("productId"));

            String coment = request.getParameter("comments");

            OrderDAO orDao = new OrderDAO();
            orDao.sendFeedBack(userId, coment, productId);
            response.sendRedirect("SendFeedback?index=" + index + "&orderId=" + orderId + "&user=" + userId);
        } catch (IOException | NumberFormatException e) {
            response.sendRedirect("SendFeedback?index=" + 1 + "&orderId=" + 53 + "&user=" + 35);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int productId = Integer.valueOf(request.getParameter("productId"));
        OrderDAO orDao = new OrderDAO();
        Product product = orDao.getProductById(productId);
        int orderId = Integer.valueOf(request.getParameter("orderId"));
        int index = Integer.valueOf(request.getParameter("index"));

        request.setAttribute("product", product);
        request.setAttribute("orderId", orderId);
        request.setAttribute("index", index);
        request.getRequestDispatcher("FeedbackDetails.jsp").forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
