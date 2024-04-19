/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.SettingDAO;
import Model.Setting;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name="SettingLists", urlPatterns={"/SettingLists"})
public class SettingLists extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SettingLists</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingLists at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String search = request.getParameter("search");
        SettingDAO settingDAO = new SettingDAO();       
        List<Setting> settings = settingDAO.getAllSettings();
        if (search != null) {
            settings = settingDAO.searchSetting(search);
        }
        
        request.setAttribute("settings", settings);
        RequestDispatcher dispatcher = request.getRequestDispatcher("SettingList.jsp");
        dispatcher.forward(request, response);
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String search = request.getParameter("search") == null ? "" : request.getParameter("search");
        String sort = request.getParameter("sort") == null ? "" : request.getParameter("sort");
        SettingDAO settingDAO = new SettingDAO();       
        List<Setting> settings = settingDAO.getAllSettings();
        if (search != null) {
            settings = settingDAO.searchSetting(search);
        }
        if (sort != null) {
            settings = settingDAO.getAllSettingSort(sort);
        }
        
        request.setAttribute("settings", settings);
        RequestDispatcher dispatcher = request.getRequestDispatcher("SettingList.jsp");
        dispatcher.forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
