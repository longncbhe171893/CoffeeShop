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
            // Get information from request
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
             int sex = Integer.valueOf(request.getParameter("sex"));
           Part imagePart = request.getPart("img");
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
            String relativeImagePath = "./image/" + uniqueFileName; // Thay đổi đường dẫn tương đối đến ảnh lưu trữ trên máy chủ
            // Xóa file cũ (nếu tồn tại)
        
            String oldImage = request.getParameter("oldImage");
            if (oldImage != null && !oldImage.isEmpty()) {
                File oldImageFile = new File(uploadDirectory, oldImage);
                if (oldImageFile.exists()) {
                    oldImageFile.delete();
                }
            }
            int roleId = Integer.parseInt(request.getParameter("role"));
             double userpoint = Double.valueOf(request.getParameter("point"));
             int id = Integer.valueOf(request.getParameter("id"));
                UserDAO udao = new UserDAO();
         
                
                // Cập nhật thông tin người dùng
<<<<<<< HEAD
//                udao.updateUser(name, email, password, address, phone, sex, image, userpoint, id);
                
=======
                udao.updateUser(name, email, password, address, phone, sex, relativeImagePath, roleId, userpoint, id);
                 response.sendRedirect("ManagerUser?index=1");
>>>>>>> ManhLD
                // Redirect to user management page
               
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
<<<<<<< HEAD
//            User user = userDAO.getUserById(Integer.valueOf(request.getParameter("userId")));
=======
            User user = userDAO.getUserById(Integer.valueOf(request.getParameter("userId")));
             ArrayList<Model.Setting> role = userDAO.getRole();
>>>>>>> ManhLD
            if(request.getParameter("UserDetail").equals("true")){
                request.setAttribute("disable", "disabled");
            }
            String title = " > Edit User";
            String action = "EditUser";
            
            request.setAttribute("title", title);
            request.setAttribute("action", action);
<<<<<<< HEAD
//            request.setAttribute("user", user);

=======
            request.setAttribute("user", user);
            request.setAttribute("rlist", role);
>>>>>>> ManhLD

            request.getRequestDispatcher("EditUser.jsp").forward(request, response);

        } catch (ServletException | IOException e) {

        }

    }
} 
