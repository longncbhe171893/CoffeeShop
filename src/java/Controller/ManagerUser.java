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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class ManagerUser extends HttpServlet {

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
        int numPage = 4;

        UserDAO udao = new UserDAO();
        // paging
        int index = Integer.valueOf(request.getParameter("index"));
        int sex=0;
        int userStatus=0;
        String search ="";
        
         ArrayList<User> userlist = udao.pagingUser(index, numPage, sex, userStatus, search);
          ArrayList<Model.Setting> role = udao.getRole();
        int count = udao.countUser(sex, userStatus,  search);
        int ePage = count / numPage;
        if (count % 4 != 0) {
            ePage++;
        }
        int nextPage, backPage;
        if (index == 1) {
            backPage = 1;
            nextPage=2;
        } else if ( index == ePage) {
            backPage=ePage-1;
            nextPage = ePage;
        } else {
            backPage = index - 1;
            nextPage = index + 1;
        }
        //set attribute for paging
        request.setAttribute("nextPage", nextPage);
        request.setAttribute("backPage", backPage);
       
        request.setAttribute("ePage", ePage);
        //set list for manage blog page
        request.setAttribute("userlist", userlist);
        request.setAttribute("rlist", role);
        request.getRequestDispatcher("ManagerUser.jsp").forward(request, response);
    }

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
        int numPage = 4;

        UserDAO udao = new UserDAO();
        // paging
        int index = Integer.valueOf(request.getParameter("index"));
        int gender =Integer.valueOf(request.getParameter("sex"));
        int uStatus = Integer.valueOf(request.getParameter("userstatus"));
        String searchName = request.getParameter("search");
        int sex=0;
        int userStatus=0;
        String search ="";
        if(gender!=0){
            sex=gender;
        }
        if(uStatus!=0){
            userStatus = uStatus;
        }
        if(searchName != null){
            search = searchName;
        }
         ArrayList<User> userlist = udao.pagingUser(index, numPage,sex, userStatus, search);
          ArrayList<Model.Setting> role = udao.getRole();
        int count = udao.countUser(sex, userStatus, searchName);
        int ePage = count / numPage;
        if (count % 4 != 0) {
            ePage++;
        }
        int nextPage, backPage;
        if (index == 1) {
            backPage = 1;
            nextPage=2;
        } else if ( index == ePage) {
            backPage=ePage-1;
            nextPage = ePage;
        } else {
            backPage = index - 1;
            nextPage = index + 1;
        }
        //set attribute for paging
        request.setAttribute("nextPage", nextPage);
        request.setAttribute("backPage", backPage);
       
        request.setAttribute("ePage", ePage);
        //set list for manage blog page
        request.setAttribute("userlist", userlist);
        request.setAttribute("rlist", role);
        request.getRequestDispatcher("ManagerUser.jsp").forward(request, response);
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
