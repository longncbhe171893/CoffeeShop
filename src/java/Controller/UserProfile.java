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
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import static jdk.nashorn.internal.objects.NativeError.getFileName;

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
        User u = (User) session.getAttribute("account");
        
         // Lấy file ảnh từ request
//        Part part = request.getPart("image");
//
//        String avatarFileName = (String) getFileName(part);
//        
//        if (!avatarFileName.isEmpty()) {
//            // lay duong dan luu tru avatar da tai len
//            String applicationPath = request.getServletContext().getRealPath("");
//            String uploadPath = applicationPath + File.separator + "avatars";
//
//            // tao thu muc luu tru neu chua co
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdirs();
//            }
//
//            // luu file da tai len
//            String avatarFilePath = uploadPath + File.separator + avatarFileName;
//            part.write(avatarFilePath);
//
//            // cập nhật đường dẫn avatar trong user
//            String avatar = "avatars" + File.separator + avatarFileName;
//            u.setImage(avatar);
//            
//        }
        if (name.length() > 50) {
            request.setAttribute("mess", "Name must be less than 50 characters");
            request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
        } else
        if (!udao.checkPhonenumber(phone)){
            request.setAttribute("mess", "Invalid phone number");
            request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
        } else
        if (address.length() > 255) {
            request.setAttribute("mess", "Address must be less than 255 characters");
            request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
        } else
        
        
        try {
            udao.UpdateUser(name, Integer.valueOf(id), sex, phone, address, udao.encodeImage(image));
            
            u.setId(Integer.valueOf(id));
            u.setName(name); 
            u.setEmail(email);
            u.setSex(sex);
            u.setPhone(phone);
            u.setAddress(address);
            u.setImage(image);
            
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
