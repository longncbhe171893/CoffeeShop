/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
@WebServlet(name="UserProfile", urlPatterns={"/UserProfile"})
public class UserProfile extends HttpServlet {
   
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
        String email = request.getParameter("email");
        int sex = Integer.parseInt(request.getParameter("sex"));
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String image = request.getParameter("image");
        String id = request.getParameter("id");
        UserDAO udao = new UserDAO();
        
        HttpSession session = request.getSession();
        if (!udao.checkPhonenumber(phone)){
            request.setAttribute("mess", "Invalid phone number");
            request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
        }
        
        
        try {
            udao.UpdateUser(name, Integer.valueOf(id), sex, phone, address, udao.encodeImage(image));
            User u = (User) session.getAttribute("account");
            u.setId(Integer.valueOf(id));
            u.setName(name);  
            u.setEmail(email);
            u.setSex(sex);
            u.setPhone(phone);
            u.setAddress(address);
            u.setImage(udao.encodeImage(image));
            
            session.setAttribute("account", u);
            request.setAttribute("mess", "Updated Success");
            request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
        } catch (Exception e) {
            response.getWriter().println(e);
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
