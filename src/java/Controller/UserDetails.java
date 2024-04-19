package Controller;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;

@MultipartConfig(
        fileSizeThreshold = 524288,
        maxFileSize = 2097152,
        maxRequestSize = 4194304,
        location = "/org"
)
public class UserDetails extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get information from request
            String action = request.getParameter("action");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
           int sex = Integer.parseInt(request.getParameter("sex"));
             Part imagePart = request.getPart("imgage");
            String userpointStr = request.getParameter("userpoint");
            double userpoint = 0.0; // Default value if not provided
            if (userpointStr != null && !userpointStr.isEmpty()) {
                userpoint = Double.parseDouble(userpointStr);
            }
              String fileName = imagePart.getSubmittedFileName();
        String uploadDirectory = getServletContext().getRealPath("/image");// Thay đổi đường dẫn tới thư mục lưu trữ ảnh trên máy chủ

        // Kiểm tra xem người dùng đã chọn ảnh hay chưa
        if (fileName != null && !fileName.isEmpty()) {
            // Tạo tên file mới
            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
            // Tạo đối tượng File mới cho ảnh
            File imageFile = new File(uploadDirectory, uniqueFileName);
            // Sao chép tệp tin ảnh vào thư mục lưu trữ
            imagePart.write(imageFile.getAbsolutePath());
            // Xử lý đường dẫn tương đối
            String image = "./image/" + uniqueFileName; // Thay đổi đường dẫn tương đối đến ảnh lưu trữ trên máy chủ
            // Xóa file cũ (nếu tồn tại)
            String oldImage = request.getParameter("oldImage");
            if (oldImage != null && !oldImage.isEmpty()) {
                File oldImageFile = new File(uploadDirectory, oldImage);
                if (oldImageFile.exists()) {
                    oldImageFile.delete();
                }
            }
            
            UserDAO udao = new UserDAO();
            
            if (action.equals("add")) {
                // Add new user
                udao.addUser(name, email, password, address, phone, sex,image, userpoint);
            } else if (action.equals("update")) {
                // Update user
                int id = Integer.valueOf(request.getParameter("id"));
                udao.updateUser(name, email, password, address, phone, sex,image, userpoint, id);
            }
            } else {
            
        }
            
            // Redirect to user management page
            response.sendRedirect("UserDetails");
        } catch (Exception e) {
            // Handle exceptions and display error message
            response.getWriter().println("An error occurred while processing your request: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "User Management Servlet";
    }
    }
