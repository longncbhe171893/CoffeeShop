/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.SellerController;

import DAO.BlogDao;
import Model.Blog;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ManageBlog extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            BlogDao blog = new BlogDao();
            ArrayList<Blog> bl = blog.getBlogs();
            ArrayList<Model.Category> cl = blog.getcategoryBlogBySettingId();
            request.setAttribute("bl", bl);
            request.setAttribute("cl", cl);
            request.getRequestDispatcher("ManageBlog.jsp").forward(request, response);

        } catch (ServletException | IOException e) {

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = request.getParameter("search");
        String firstDate = request.getParameter("firstDate");
        String secondDate = request.getParameter("secondDate");

        BlogDao blogDao = new BlogDao();
        List<Blog> blogList = blogDao.getBlogs();

        if (search != null) {
            blogList = blogDao.searchBlog(search);
        } else if (firstDate != null && secondDate != null) {
            Date fdate = Date.valueOf(firstDate);
            Date sdate = Date.valueOf(secondDate);
            blogList = blogDao.getBlogByDate(fdate, sdate);
        }

        request.setAttribute("bl", blogList);
        request.getRequestDispatcher("ManageBlog.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
