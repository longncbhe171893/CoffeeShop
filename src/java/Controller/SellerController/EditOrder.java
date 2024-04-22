/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.SellerController;

import DAO.OrderDAO;
import Model.Order;
import Model.OrderDetail;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

            if (edit) {
                String tittle = "> Edit Order";
                Order order = orDao.getOrderById(orderId);
                ArrayList<OrderDetail> orderDetails = orDao.getOrderDetail(orderId);
                double totalAmount = 0.0;

                for (OrderDetail orderDetail : orderDetails) {
                    double amount = orderDetail.getAmount() - (orderDetail.getAmount() * orderDetail.getOrder().getDiscount() / 100);
                    totalAmount += amount;
                }
                request.setAttribute("order", order);
                request.setAttribute("totalAmount", totalAmount);

                request.setAttribute("orderDetails", orderDetails);
                request.setAttribute("tittle", tittle);
                request.getRequestDispatcher("EditOrder.jsp").forward(request, response);
            }

        } catch (ServletException | IOException e) {

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
