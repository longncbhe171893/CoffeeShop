/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.SellerController;

import DAO.OrderDAO;
import Model.Order;
import Model.OrderDetail;
import java.io.IOException;
<<<<<<< HEAD
=======
import java.io.PrintWriter;
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import java.util.ArrayList;

@MultipartConfig(
        fileSizeThreshold = 524288,
        maxFileSize = 2097152,
        maxRequestSize = 4194304,
        location = "/org"
)
public class EditOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String index = request.getParameter("index");
        try {
            int orderId = Integer.valueOf(request.getParameter("orderId"));
            String orderName = request.getParameter("orderName");
            int orderDiscount = Integer.valueOf(request.getParameter("orderDiscount"));
            String orderNote = request.getParameter("orderNote");
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.updateOrder(orderId, orderName, orderDiscount, orderNote);
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("404Err.jsp").forward(request, response);
        }
        request.setAttribute("message", "Successfully");
        response.sendRedirect("ManageOrder?index=" + index);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String index = request.getParameter("index");
            request.setAttribute("index", index);
            OrderDAO orDao = new OrderDAO();
            int orderId = Integer.valueOf(request.getParameter("orderId"));
            boolean edit = Boolean.valueOf(request.getParameter("edit"));

=======
import jakarta.servlet.http.Part;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

/**
 *
 * @author anhvu
 */
public class EditOrder extends HttpServlet {

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
        try {
            OrderDAO orDao = new OrderDAO();
            int orderId = Integer.valueOf(request.getParameter("orderId"));
            boolean edit = Boolean.valueOf(request.getParameter("edit"));
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
            if (edit) {
                String tittle = "> Edit Order";
                Order order = orDao.getOrderById(orderId);
                ArrayList<OrderDetail> orderDetails = orDao.getOrderDetail(orderId);
                double totalAmount = 0.0;
<<<<<<< HEAD

=======
                
                
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
                for (OrderDetail orderDetail : orderDetails) {
                    double amount = orderDetail.getAmount() - (orderDetail.getAmount() * orderDetail.getOrder().getDiscount() / 100);
                    totalAmount += amount;
                }
                request.setAttribute("order", order);
                request.setAttribute("totalAmount", totalAmount);
<<<<<<< HEAD

                request.setAttribute("orderDetails", orderDetails);
                request.setAttribute("tittle", tittle);
                request.getRequestDispatcher("EditOrder.jsp").forward(request, response);
            }

=======
                request.setAttribute("orderDetails", orderDetails);
                request.setAttribute("tittle", tittle);
            }
            request.getRequestDispatcher("EditOrder.jsp").forward(request, response);
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
        } catch (ServletException | IOException e) {

        }
    }

<<<<<<< HEAD
=======
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

<<<<<<< HEAD
=======
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
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String orderName = request.getParameter("orderName");
        Timestamp orderDateTime = Timestamp.valueOf("orderDate");
        String orderDiscount = request.getParameter("orderDiscount");
        String orderNote = request.getParameter("orderNote");
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
