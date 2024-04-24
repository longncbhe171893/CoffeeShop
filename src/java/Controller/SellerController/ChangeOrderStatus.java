/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.SellerController;

<<<<<<< HEAD
import DAO.OrderDAO;
import java.io.IOException;
=======
import DAO.BlogDao;
import DAO.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author anhvu
 */
public class ChangeOrderStatus extends HttpServlet {

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
        OrderDAO orDao = new OrderDAO();
        int id = Integer.valueOf(request.getParameter("orderId"));
        int status = Integer.valueOf(request.getParameter("ost"));
<<<<<<< HEAD
        String index = request.getParameter("index");
        switch (status) {
            case 3:
                orDao.UpdateStatusOrder(status, id);
                break;
            case 2:
                orDao.UpdateStatusOrder(4, id);
                break;
            case 1:
                orDao.UpdateStatusOrder(2, id);
                break;
            default:
                break;
        }

        response.sendRedirect("ManageOrder?index=" + index);

=======
        if (status == 3) {
            orDao.UpdateStatusOrder(status, id);
        } else if (status == 2) {
            orDao.UpdateStatusOrder(4, id);
        } else if (status == 1) {
            orDao.UpdateStatusOrder(2, id);
        }

        response.sendRedirect("./ManageOrder");
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2

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
        processRequest(request, response);
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
