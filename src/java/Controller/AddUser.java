///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package Controller;
//
//import DAO.UserDAO;
//import Model.User;
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.Part;
//import java.io.File;
//
//@MultipartConfig(
//        fileSizeThreshold = 524288,
//        maxFileSize = 2097152,
//        maxRequestSize = 4194304,
//        location = "/org"
//)
//public class AddUser extends HttpServlet {
// protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try {
//            String title = " > Add User";
//            String action = "AddUser";
//            UserDAO user = new UserDAO();
//            request.setAttribute("title", title);
//            request.setAttribute("action", action);
//
//            request.getRequestDispatcher("EditUser.jsp").forward(request, response);
//
//        } catch (ServletException | IOException e) {
//
//        }
//
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//
//    }
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            // Get information from request
//            String name = request.getParameter("name");
//            String email = request.getParameter("email");
//            String password = request.getParameter("password");
//            String address = request.getParameter("address");
//            String phone = request.getParameter("phone");
//            int sex = Integer.parseInt(request.getParameter("sex"));
//            Part imagePart = request.getPart("imgage");
//            String userpointStr = request.getParameter("userpoint");
//            double userpoint = 0.0; // Default value if not provided
//            if (userpointStr != null && !userpointStr.isEmpty()) {
//                userpoint = Double.parseDouble(userpointStr);
//            }
//            String fileName = imagePart.getSubmittedFileName();
//            String uploadDirectory = getServletContext().getRealPath("/image");
//
//            // Kiểm tra xem người dùng đã chọn ảnh hay chưa
//            if (fileName != null && !fileName.isEmpty()) {
//                // Tạo tên file mới
//                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
//                // Tạo đối tượng File mới cho ảnh
//                File imageFile = new File(uploadDirectory, uniqueFileName);
//                // Sao chép tệp tin ảnh vào thư mục lưu trữ
//                imagePart.write(imageFile.getAbsolutePath());
//                // Xử lý đường dẫn tương đối
//                String image = "./image/" + uniqueFileName;
//                String oldImage = request.getParameter("oldImage");
//            if (oldImage != null && !oldImage.isEmpty()) {
//                File oldImageFile = new File(uploadDirectory, oldImage);
//                if (oldImageFile.exists()) {
//                    oldImageFile.delete();
//                }
//            }
//
//                UserDAO udao = new UserDAO();
//                
//                // Thêm người dùng mới
//                udao.addUser(name, email, password, address, phone, sex, image, userpoint);
//                
//                // Redirect to user management page
//                response.sendRedirect("./ManagerUser");
//            } else {
//               
//            }
//        } catch (Exception e) {
//            // Xử lý ngoại lệ và hiển thị thông báo lỗi
//            response.getWriter().println("An error occurred while processing your request: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public String getServletInfo() {
//       return "Short description";
//    }
//}