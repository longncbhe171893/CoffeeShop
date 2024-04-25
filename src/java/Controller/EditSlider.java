/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;


import DAO.SliderDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class EditSlider extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Lấy thông tin từ request
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        String status = request.getParameter("status");
        
        // Lấy thông tin về file ảnh từ request
        Part imagePart = request.getPart("image");
        String fileName = imagePart.getSubmittedFileName();
        
        

//         Kiểm tra xem người dùng đã chọn ảnh mới hay không
        if (fileName != null && !fileName.isEmpty()) {
            // Tạo tên file mới
            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
            
            // Lấy đường dẫn thư mục lưu trữ ảnh trên máy chủ
            String uploadDirectory = getServletContext().getRealPath("/image");
            
            // Tạo đối tượng File mới cho ảnh
            File imageFile = new File(uploadDirectory, uniqueFileName);
            
            // Lưu trữ file ảnh vào thư mục lưu trữ
            imagePart.write(imageFile.getAbsolutePath());
            
            // Xử lý đường dẫn tương đối đến ảnh mới
            String imagePath = "./image/" + uniqueFileName;
            
            // Cập nhật thông tin của slider với ảnh mới
            SliderDAO sliderDAO = new SliderDAO();
            sliderDAO.updateSliderWithImage(title, imagePath,url, id, status);
        } else {
            // Nếu người dùng không chọn ảnh mới, chỉ cập nhật thông tin khác của slider
            SliderDAO productDAO = new SliderDAO();
            productDAO.updateSliderWithoutImage(title,url, id, status);
        }

        // Chuyển hướng về trang quản lý slider
        response.sendRedirect("ManageSlider");
           
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
