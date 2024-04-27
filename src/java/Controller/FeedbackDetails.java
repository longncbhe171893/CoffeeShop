/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Contact;
import DAO.ContactDAO;
import DAO.FeedbackDAO;
import Model.Feedback;
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
@WebServlet(name = "FeedbackDetails", urlPatterns = {"/FeedbackDetails"})
public class FeedbackDetails extends HttpServlet {

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
        int feedback_id = Integer.parseInt(request.getParameter("feedback_id"));
        Feedback feedback = new Feedback();
        FeedbackDAO dao = new FeedbackDAO();
        feedback = dao.getFeedbackDetail(feedback_id);
        request.setAttribute("feedback", feedback);
        request.getRequestDispatcher("FeedbackDetails.jsp").forward(request, response);
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
        int feedback_id = Integer.parseInt(request.getParameter("feedback_id"));
        String user_id = request.getParameter("user_id");
        String content = request.getParameter("content");
        String product_id = request.getParameter("product_id");
        String note = request.getParameter("note");

        
        FeedbackDAO dao = new FeedbackDAO();
        if (note.length() > 255){
            request.setAttribute("mess", "The note must be less than 255 character");
            Feedback feedback = new Feedback();
            request.setAttribute("note", note);
            feedback = dao.getFeedbackDetail(feedback_id);
            request.setAttribute("feedback", feedback);
            request.getRequestDispatcher("FeedbackDetails.jsp").forward(request, response);
        } else {
        
        
        dao.updateFeedbackNote(note, feedback_id);
        request.setAttribute("mess", "Update successfully");
        Feedback feedback = new Feedback();
        feedback = dao.getFeedbackDetail(feedback_id);
            request.setAttribute("feedback", feedback);
            request.getRequestDispatcher("FeedbackDetails.jsp").forward(request, response);
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
