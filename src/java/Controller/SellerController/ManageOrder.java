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
        String mess, messEdit;
        try {
            mess = request.getParameter("mess");
            messEdit = request.getParameter("messEdit");
            request.setAttribute("mess", mess);
            request.setAttribute("messEdit", messEdit);
        } catch (Exception e) {
            request.setAttribute("mess", "");
        }
        ArrayList<Model.User> creator;
        creator = dao.getAllCostomer();
        request.setAttribute("creator", creator);

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
            nextPage = 2;
        } else if (index == ePage) {
            backPage = ePage - 1;
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
        request.setAttribute("index", index);
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
        OrderDAO orderDao = new OrderDAO();
        ArrayList<Model.User> creator;
        String search = request.getParameter("search");
        String firstDate = request.getParameter("firstDate");
        String secondDate = request.getParameter("secondDate");
        ArrayList<Order> orderList = orderDao.pagingOrder(1, 4);
        try {
            creator = orderDao.getAllCostomer();
//        List<Model.> status = orderDao.getcategoryBlogByType();

            request.setAttribute("creator", creator);

            if (search != null) {
                int searchNum = Integer.valueOf(search);
                if (searchNum < 1000) {
                    orderList = orderDao.searchOrderByIdOrPhone(searchNum);
                } else {
                    orderList = orderDao.searchOrderByIdOrPhone(search);
                }
                request.setAttribute("olist", orderList);
            } else if (firstDate != null && secondDate != null) {
                Date fdate = Date.valueOf(firstDate);
                Date sdate = Date.valueOf(secondDate);
                orderList = orderDao.getOrderByDate(fdate, sdate);
                request.setAttribute("olist", orderList);
            }
            request.setAttribute("olist", orderList);
            request.setAttribute("index", 1);
            request.getRequestDispatcher("ManageOrder.jsp").forward(request, response);

        } catch (NumberFormatException e) {

            String mess = "please input number or phone number";
            response.sendRedirect("ManageOrder?index=" + 1 + "&mess=" + mess);
        }

//        request.setAttribute("categoryBlog", category);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
