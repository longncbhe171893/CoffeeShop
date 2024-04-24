/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.SellerController;

import DAO.OrderDAO;
import Model.Order;
import Model.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

/**
 *
 * @author anhvu
 */
public class EditOrder extends HttpServlet {

<<<<<<< HEAD
=======
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrderDAO oDao = new OrderDAO();
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int index = Integer.parseInt(request.getParameter("index"));
            int orderDiscount = Integer.parseInt(request.getParameter("orderDiscount"));
            String orderNote = request.getParameter("orderNote");
            oDao.updateOrder(orderId, orderDiscount, orderNote);
            String mess = "Update successfuly";
            response.sendRedirect("ManageOrder?index=" + index + "&mess=" + mess);

        } catch (NumberFormatException e) {

            int index = Integer.parseInt(request.getParameter("index"));
            String mess = "Update Failed!";
            response.sendRedirect("ManageOrder?index=" + index + "&messEdit=" + mess);
        }

    }

>>>>>>> 0e50b69a349360feb864dc2a4cc1cb4ae5fce920
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
<<<<<<< HEAD
            boolean edit = Boolean.valueOf(request.getParameter("edit"));
=======
            int index = Integer.valueOf(request.getParameter("index"));
            boolean edit = Boolean.valueOf(request.getParameter("edit"));
            String mess;
            try {
                mess = request.getParameter("mess");
                request.setAttribute("mess", mess);
            } catch (Exception e) {
                request.setAttribute("mess", "");
            }
>>>>>>> 0e50b69a349360feb864dc2a4cc1cb4ae5fce920
            if (edit) {
                String tittle = "> Edit Order";
                Order order = orDao.getOrderById(orderId);
                ArrayList<OrderDetail> orderDetails = orDao.getOrderDetail(orderId);
                double totalAmount = 0.0;
<<<<<<< HEAD
                
                
=======

>>>>>>> 0e50b69a349360feb864dc2a4cc1cb4ae5fce920
                for (OrderDetail orderDetail : orderDetails) {
                    double amount = orderDetail.getAmount() - (orderDetail.getAmount() * orderDetail.getOrder().getDiscount() / 100);
                    totalAmount += amount;
                }
                request.setAttribute("order", order);
<<<<<<< HEAD
=======
                request.setAttribute("index", index);
>>>>>>> 0e50b69a349360feb864dc2a4cc1cb4ae5fce920
                request.setAttribute("totalAmount", totalAmount);
                request.setAttribute("orderDetails", orderDetails);
                request.setAttribute("tittle", tittle);
            }
            request.getRequestDispatcher("EditOrder.jsp").forward(request, response);
        } catch (ServletException | IOException e) {

        }
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
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
<<<<<<< HEAD
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

=======
>>>>>>> 0e50b69a349360feb864dc2a4cc1cb4ae5fce920
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
