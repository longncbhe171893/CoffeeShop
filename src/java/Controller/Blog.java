/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.BlogDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
@WebServlet(name = "Blog", urlPatterns = {"/Blog"})
public class Blog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BlogDao blog = new BlogDao();
        List<Model.Blog> lst = blog.getBlogs(); // danh sach blog
        int page;
        int numberForPage = 2; // so blog trong 1 trang
        int size = lst.size(); // tong so luong blog
        int numberOfPage = (size % 2 == 0 ? (size / 2) : ((size / 2) + 1)); // tong so luong trang
        String xPage = request.getParameter("page");
        if (xPage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xPage);
        }
        int start = (page - 1) * numberForPage;
        int end = Math.min(numberForPage * page, size);
        List<Model.Blog> list = blog.getListByPage(lst, start, end);
        ArrayList<Model.Blog> bl = blog.recentBlog();
        request.setAttribute("bl", bl);
        request.setAttribute("data", list);
        request.setAttribute("page", page);
        request.setAttribute("numberOfPage", numberOfPage);
        request.getRequestDispatcher("BlogList.jsp").forward(request, response);
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
        String cid = request.getParameter("cid") == null ? "" : request.getParameter("cid");
        String search = request.getParameter("search2") == null ? "" : request.getParameter("search2");
        search = search.trim();
        int c = Integer.parseInt(cid);
        BlogDao b = new BlogDao();
        ArrayList<Model.Blog> blist = b.getBlogByCategory(c);
        ArrayList<Model.Blog> blist1 = b.getBlogBySearchTilte(search);
        
        ArrayList<Model.Blog> bl = b.recentBlog();
        request.setAttribute("bl", bl);
        request.setAttribute("blist", blist);
        request.setAttribute("blist1", blist1);
        request.getRequestDispatcher("SearchBlogList.jsp").forward(request, response);
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
