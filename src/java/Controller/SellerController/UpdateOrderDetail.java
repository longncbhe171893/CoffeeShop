/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.SellerController;

import DAO.OrderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author anhvu
 */
public class UpdateOrderDetail extends HttpServlet {

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
<<<<<<< HEAD
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String index = request.getParameter("index");
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));
        int size = Integer.parseInt(request.getParameter("size"));

        int orderDetailId = Integer.parseInt(request.getParameter("orderDetail"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        OrderDAO orDao = new OrderDAO();
        orDao.updateOrderDetail(productPrice, orderDetailId, quantity, size);

        response.sendRedirect("EditOrder?orderId=" + orderId + "&edit=true&editOrderDetail=false&index=" + index);
=======
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2

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
<<<<<<< HEAD
        processRequest(request, response);
=======
        response.setContentType("text/html;charset=UTF-8");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));
        int size = Integer.parseInt(request.getParameter("size"));

        int orderDetailId = Integer.parseInt(request.getParameter("orderDetail"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        OrderDAO orDao = new OrderDAO();
        orDao.updateOrderDetail(productPrice, orderDetailId, quantity, size);

        response.sendRedirect("EditOrder?orderId=" + orderId + "&edit=true");
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2

    }

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
        processRequest(request, response);
<<<<<<< HEAD

=======
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
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
