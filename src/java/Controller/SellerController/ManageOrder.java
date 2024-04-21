/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.SellerController;

import DAO.OrderDAO;
import Model.Order;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;

public class ManageOrder extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int numPage = 4;

        OrderDAO dao = new OrderDAO();
        // paging
        int index = Integer.valueOf(request.getParameter("index"));
        int count = dao.countOrder();
        int ePage = count / numPage;
        if (count % 4 != 0) {
            ePage++;
        }
        int nextPage, backPage;
        if (index == 1) {
            backPage = 1;
            nextPage=2;
        } else if ( index == ePage) {
            backPage=ePage-1;
            nextPage = ePage;
        } else {
            backPage = index - 1;
            nextPage = index + 1;
        }
        //set attribute for paging
        request.setAttribute("nextPage", nextPage);
        request.setAttribute("backPage", backPage);
        ArrayList<Order> orderList = dao.pagingOrder(index, numPage);
        request.setAttribute("ePage", ePage);
        //set list for manage blog page
        request.setAttribute("olist", orderList);
        request.getRequestDispatcher("ManageOrder.jsp").forward(request, response);
    }

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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstDate = request.getParameter("firstDate");
        String secondDate = request.getParameter("secondDate");
        OrderDAO dao = new OrderDAO();
        Date fdate = Date.valueOf(firstDate);
        Date sdate = Date.valueOf(secondDate);
        ArrayList<Order> orderList = dao.getOrderByDate(fdate, sdate);
        request.setAttribute("olist", orderList);
        request.getRequestDispatcher("ManageOrder.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
