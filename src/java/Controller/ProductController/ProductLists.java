/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.ProductController;

import DAO.ProductDAO;
import Model.Product;
import Model.Setting;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

public class ProductLists extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String categoryId = "4";//request.getParameter("setting_id") == null ? "" : request.getParameter("setting_id");
            String search = request.getParameter("search") == null ? "" : request.getParameter("search");
            String sort = request.getParameter("sort") == null ? "" : request.getParameter("sort");
            // categoryId = request.getParameter("cid") == null ? "" : request.getParameter("cid");
            search = search.trim();
            ProductDAO pdao = new ProductDAO();
            ArrayList<Setting> clist = pdao.getCategory();

            int totalproduct = pdao.getNumberProduct(categoryId, search);
            int numberPage = (int) Math.ceil((double) totalproduct / 9);
            int index;
            String currentPage = request.getParameter("index");
            if (currentPage == null) {
                index = 1;
            } else {
                index = Integer.parseInt(currentPage);
            }
//            if (categoryId.equals("")) {
//                ArrayList<Product> plist = pdao.getAllProducts();
//                request.setAttribute("plist", plist);
//
//            } else 
            //if (categoryId.equals("4")) {
            ArrayList<Product> plist = pdao.getAllProduct(categoryId, search, index, sort);
            request.setAttribute("plist", plist);
            // }
            request.setAttribute("numberPage", numberPage);
            request.setAttribute("clist", clist);
            request.getRequestDispatcher("ProductLists.jsp").forward(request, response);
        } catch (Exception e) {
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String categoryId = "4";
            // request.getParameter("setting_id") == null ? "" : request.getParameter("setting_id");
            String search = request.getParameter("search1") == null ? "" : request.getParameter("search1");
            String sort = request.getParameter("sort") == null ? "" : request.getParameter("sort");
            String cid = request.getParameter("cid") == null ? "" : request.getParameter("cid");
            search = search.trim();
            ProductDAO pdao = new ProductDAO();
            ArrayList<Setting> clist = pdao.getCategory();
            int totalproduct = pdao.getNumberProduct(categoryId, search);
            int numberPage = (int) Math.ceil((double) totalproduct / 9);
            int index;
            String currentPage = request.getParameter("index");
            if (currentPage == null) {
                index = 1;
            } else {
                index = Integer.parseInt(currentPage);
            }
            if (!cid.equals("")) {
                categoryId = cid;
            }

            ArrayList<Product> plist = pdao.getAllProduct(categoryId, search, index, sort);
            request.setAttribute("numberPage", numberPage);
            request.setAttribute("plists", plist);
            request.setAttribute("clist", clist);
            request.getRequestDispatcher("SearchProduct.jsp").forward(request, response);
        } catch (Exception e) {
        }
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
