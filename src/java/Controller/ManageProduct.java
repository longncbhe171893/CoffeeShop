/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import Model.Product;
import Model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Hoàng Vũ
 */
public class ManageProduct extends HttpServlet {

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
        int numPage = 4;

        ProductDAO pdao = new ProductDAO();
        // paging
        int index = Integer.valueOf(request.getParameter("index"));
         ArrayList<Product> productlist = pdao.pagingProduct(index, numPage);
        int count = pdao.countProduct();
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
       
        request.setAttribute("ePage", ePage);
        //set list for manage blog page
        request.setAttribute("productlist", productlist);
        request.getRequestDispatcher("ManageProduct.jsp").forward(request, response);
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
        String search = request.getParameter("search");
        String firstDate = request.getParameter("firstDate");
        String secondDate = request.getParameter("secondDate");

        ProductDAO pdao = new ProductDAO();
        ArrayList<Product> productlist = new ArrayList<>();

        if (search != null && !search.isEmpty()) {
            productlist = pdao.searchProduct(search);
        } else if (firstDate != null && secondDate != null && !firstDate.isEmpty() && !secondDate.isEmpty()) {
            Date fdate = Date.valueOf(firstDate);
            Date sdate = Date.valueOf(secondDate);
            productlist = pdao.getProductByDate(fdate, sdate);
        }

        request.setAttribute("productlist", productlist);
        request.getRequestDispatcher("ManageProduct.jsp").forward(request, response);
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
