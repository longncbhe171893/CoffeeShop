/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.BlogDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "BlogController", urlPatterns = {"/blogController"})
public class BlogController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BlogDao blogDao = new BlogDao();
        ArrayList<Model.Blog> bl = blogDao.recentBlog();
        String blogId = (request.getParameter("blogId"));
        request.setAttribute("blog", blogDao.getBlogByBlogId(Integer.parseInt(blogId)));
        request.setAttribute("bl", bl);
        System.out.println(blogId);
        request.getRequestDispatcher("BlogDetails.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = request.getParameter("search");
        BlogDao blogDao = new BlogDao();

        List<Model.Blog> blogList;

        if (search != null && !search.isEmpty()) {
            blogList = blogDao.searchBlog(search);
        } else {
            blogList = blogDao.getBlogs();
        }

        request.setAttribute("data", blogList);
        request.getRequestDispatcher("BlogList.jsp").forward(request, response);
    }
    
}
