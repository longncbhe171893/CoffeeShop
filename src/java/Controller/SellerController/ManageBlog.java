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
            int numPage = 4;
            BlogDao blog = new BlogDao();
            int index = Integer.valueOf(request.getParameter("index"));
            int count = blog.countBlog();
            int ePage = count / numPage;
            if (count % 4 != 0) {
                ePage++;
            }
            ArrayList<Blog> bl = blog.pagingBlogs(index, numPage);
            ArrayList<Model.User> creator = blog.getAllSeller();
            List<Model.Category> category = blog.getcategoryBlogByType();
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
            request.setAttribute("nextPage", nextPage);
            request.setAttribute("backPage", backPage);
            request.setAttribute("index", index);
            request.setAttribute("ePage", ePage);
            request.setAttribute("bl", bl);
            request.setAttribute("creator", creator);
            request.setAttribute("categoryBlog", category);
            
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
        BlogDao blogDao = new BlogDao();
        
        String search = request.getParameter("search");
        String firstDate = request.getParameter("firstDate");
        String secondDate = request.getParameter("secondDate");
        
        ArrayList<Model.User> creator = blogDao.getAllSeller();
        List<Model.Category> category = blogDao.getcategoryBlogByType();
        
        int numPage = 4;
        int index = Integer.valueOf(request.getParameter("index"));
        int count = blogDao.countBlog();
        int ePage = count / numPage;
        if (count % 4 != 0) {
            ePage++;
        }
        List<Blog> blogList = blogDao.pagingBlogs(index, numPage);
        if (search != null) {
            blogList = blogDao.searchBlog(search);
        } else if (firstDate != null && secondDate != null) {
            Date fdate = Date.valueOf(firstDate);
            Date sdate = Date.valueOf(secondDate);
            blogList = blogDao.getBlogByDate(fdate, sdate);
        }
        
        request.setAttribute("ePage", ePage);
        request.setAttribute("creator", creator);
        request.setAttribute("index", index);
        request.setAttribute("categoryBlog", category);
        request.setAttribute("bl", blogList);
        request.getRequestDispatcher("ManageBlog.jsp").forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}
