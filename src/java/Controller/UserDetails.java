///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package Controller;
//
//import DAO.UserDAO;
//import java.io.IOException;
//import jakarta.servlet.annotation.MultipartConfig;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.sql.Date;
//import jakarta.servlet.http.Part;
//import java.io.File;
//
//@MultipartConfig(
//        fileSizeThreshold = 524288,
//        maxFileSize = 2097152,
//        maxRequestSize = 4194304,
//        location = "/org"
//)
///**
// *
// * @author Hoàng Vũ
// */
//public class UserDetails extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
////        try {
//        response.setContentType("text/html;charset=UTF-8");
//       String name = request.getParameter("name");
//            String email = request.getParameter("email");
//            String password = request.getParameter("password");
//            String address = request.getParameter("address");
//            String phone = request.getParameter("phone");
//            String sex = request.getParameter("sex");
//           Part imagePart = request.getPart("image");
//            String userpointStr = request.getParameter("userpoint");
//            double userpoint = 0.0; // Default value if not provided
//            if (userpointStr != null && !userpointStr.isEmpty()) {
//                userpoint = Double.parseDouble(userpointStr);
//            }
//              int id = Integer.valueOf(request.getParameter("id"));
//            String fileName = imagePart.getSubmittedFileName();
//        String uploadDirectory = getServletContext().getRealPath("/image");// Thay đổi đường dẫn tới thư mục lưu trữ ảnh trên máy chủ
//        // Kiểm tra xem người dùng đã chọn ảnh hay chưa
//        if (fileName != null && !fileName.isEmpty()) {
//            // Tạo tên file mới
//            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
//            File imageFile = new File(uploadDirectory, uniqueFileName);
//            // Sao chép tệp tin ảnh vào thư mục lưu trữ
//            imagePart.write(imageFile.getAbsolutePath());
//            // Xử lý đường dẫn tương đối
//            String relativeImagePath = "./image/" + uniqueFileName; // Thay đổi đường dẫn tương đối đến ảnh lưu trữ trên máy chủ
//            // Xóa file cũ (nếu tồn tại)
//            String oldImage = request.getParameter("oldImage");
//            if (oldImage != null && !oldImage.isEmpty()) {
//                File oldImageFile = new File(uploadDirectory, oldImage);
//                if (oldImageFile.exists()) {
//                    oldImageFile.delete();
//                }
//            }
//          
//        UserDAO udao = new UserDAO();
//        udao.updateUser(name, email, password, address, phone, sex, relativeImagePath, userpoint, id);
//        response.sendRedirect("ManagerUser");
////        } catch (Exception e) {
////            response.sendRedirect("./404.html");
////        }
//    }
//    }
//
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//}
