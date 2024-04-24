package Controller;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig(
        fileSizeThreshold = 524288,
        maxFileSize = 2097152,
        maxRequestSize = 4194304,
        location = "/org"
)
public class EditUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get information from request
            int id = Integer.valueOf(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            int sex = Integer.parseInt(request.getParameter("sex"));
            Part imagePart = request.getPart("image");
            String userpointStr = request.getParameter("userpoint");
            double userpoint = 0.0; // Default value if not provided
            if (userpointStr != null && !userpointStr.isEmpty()) {
                userpoint = Double.parseDouble(userpointStr);
            }
            String fileName = imagePart.getSubmittedFileName();
            String uploadDirectory = getServletContext().getRealPath("/image");

            // Kiểm tra xem người dùng đã chọn ảnh hay chưa
            if (fileName != null && !fileName.isEmpty()) {
                // Tạo tên file mới
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                // Tạo đối tượng File mới cho ảnh
                File imageFile = new File(uploadDirectory, uniqueFileName);
                // Sao chép tệp tin ảnh vào thư mục lưu trữ
                imagePart.write(imageFile.getAbsolutePath());
                // Xử lý đường dẫn tương đối
                String image = "./image/" + uniqueFileName; 
                String oldImage = request.getParameter("oldImage");
            if (oldImage != null && !oldImage.isEmpty()) {
                File oldImageFile = new File(uploadDirectory, oldImage);
                if (oldImageFile.exists()) {
                    oldImageFile.delete();
                }
            }

                UserDAO udao = new UserDAO();
                
                // Cập nhật thông tin người dùng
//                udao.updateUser(name, email, password, address, phone, sex, image, userpoint, id);
                
                // Redirect to user management page
                response.sendRedirect("ManagerUser");
            } 
        } catch (Exception e) {
            // Xử lý ngoại lệ và hiển thị thông báo lỗi
            response.getWriter().println("An error occurred while processing your request: " + e.getMessage());
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            UserDAO userDAO = new UserDAO();
//            User user = userDAO.getUserById(Integer.valueOf(request.getParameter("userId")));
            if(request.getParameter("UserDetail").equals("true")){
                request.setAttribute("disable", "disabled");
            }
            String title = " > Edit User";
            String action = "EditUser";
            
            request.setAttribute("title", title);
            request.setAttribute("action", action);
//            request.setAttribute("user", user);


            request.getRequestDispatcher("EditUser.jsp").forward(request, response);

        } catch (ServletException | IOException e) {

        }

    }
} 
