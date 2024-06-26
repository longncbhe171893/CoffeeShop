/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.SettingDAO;
import Model.Setting;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name="AddSetting", urlPatterns={"/AddSetting"})
public class AddSetting extends HttpServlet {
   
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
            out.println("<title>Servlet AddSetting</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSetting at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String type = request.getParameter("type");
        int status = Integer.parseInt(request.getParameter("status"));
        HttpSession session = request.getSession();
        request.setAttribute("sname", name);
        request.setAttribute("sdescription", description);
        SettingDAO sdao = new SettingDAO();
        if (name.length() > 50 ) {
            List<Setting> settings = sdao.getAllSettings();
            request.setAttribute("settings", settings);
            request.setAttribute("mess", "Setting name must be less than 50 characters");
            request.getRequestDispatcher("SettingList.jsp?id=myModalAddNew").forward(request, response);
        } else
        if (description.length() > 255) {
            List<Setting> settings = sdao.getAllSettings();
            request.setAttribute("settings", settings);
            request.setAttribute("mess", "Description must be less than 255 characters");
            request.getRequestDispatcher("SettingList.jsp").forward(request, response);
        } else
        
        if (sdao.checkSettingNameAndTypeExist(name, type)){
            List<Setting> settings = sdao.getAllSettings();
            request.setAttribute("settings", settings);
            request.setAttribute("mess", "This setting is existed");
            request.getRequestDispatcher("SettingList.jsp").forward(request, response);
        } else {
        sdao.addSetting(name, description, type, status);
        request.setAttribute("mess", "Add setting successfully");
        
        List<Setting> settings = sdao.getAllSettings();
        request.setAttribute("settings", settings);
        request.getRequestDispatcher("SettingList.jsp").forward(request, response);
        }
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
