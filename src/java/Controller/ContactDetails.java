/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;
import Model.Contact;
import DAO.ContactDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(name="ContactDetails", urlPatterns={"/ContactDetails"})
public class ContactDetails extends HttpServlet {
   
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
        int contact_id = Integer.parseInt(request.getParameter("contact_id"));
        Contact contact = new Contact();
        ContactDAO dao = new ContactDAO();
        contact = dao.getContactDetail(contact_id);
        request.setAttribute("contact", contact);
        request.getRequestDispatcher("ContactDetails.jsp").forward(request, response);
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
        int contact_id = Integer.parseInt(request.getParameter("contact_id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int setting_id = Integer.parseInt(request.getParameter("setting_id"));
        String message = request.getParameter("message");
        String subject = request.getParameter("subject");
        int status = Integer.parseInt(request.getParameter("status"));
        String note = request.getParameter("note");
        ContactDAO dao = new ContactDAO();
        
        if (note.length() > 255){
            request.setAttribute("mess", "The note must be less than 255 character");
            Contact contact = new Contact();
            request.setAttribute("note", note);
            contact = dao.getContactDetail(contact_id);
            request.setAttribute("contact", contact);
            request.getRequestDispatcher("ContactDetails.jsp").forward(request, response);
        } else {
        
        
        dao.updateContactNoteAndStatus(status, note, contact_id);
        request.setAttribute("mess", "Update successfully");
        Contact contact = new Contact();
        contact = dao.getContactDetail(contact_id);
            request.setAttribute("contact", contact);
            request.getRequestDispatcher("ContactDetails.jsp").forward(request, response);
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
