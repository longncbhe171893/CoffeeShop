/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;

/**
 *
 * @author Hoàng Vũ
 */
@MultipartConfig(
        fileSizeThreshold = 524288,
        maxFileSize = 2097152,
        maxRequestSize = 4194304,
        location = "/org"
)
public class EditProduct extends HttpServlet {
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    int id = Integer.valueOf(request.getParameter("id"));
    //int cateId = Integer.valueOf(request.getParameter("category"));
    double price = Double.valueOf(request.getParameter("price"));
    Part imagePart = request.getPart("img"); // Lấy thông tin về file ảnh
    String descri = request.getParameter("descri");
    String name = request.getParameter("name");
    int size = Integer.valueOf(request.getParameter("size"));
    
    String fileName = imagePart.getSubmittedFileName(); // Lấy tên file ảnh
    String uploadDirectory = getServletContext().getRealPath("/image"); // Thư mục lưu trữ ảnh trên máy chủ

    // Kiểm tra xem người dùng đã chọn ảnh hay chưa
    if (fileName != null && !fileName.isEmpty()) {
        // Tạo tên file mới
        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
        // Tạo đối tượng File mới cho ảnh
        File imageFile = new File(uploadDirectory, uniqueFileName);
        // Sao chép tệp tin ảnh vào thư mục lưu trữ
        imagePart.write(imageFile.getAbsolutePath());
        // Xử lý đường dẫn tương đối
        String relativeImagePath = "./image/" + uniqueFileName; // Đường dẫn tương đối đến ảnh lưu trữ trên máy chủ
        
        // Tiếp tục xử lý và lưu thông tin từ form vào cơ sở dữ liệu
       
        
        // Xóa file ảnh cũ (nếu tồn tại)
        String oldImage = request.getParameter("oldImage");
        if (oldImage != null && !oldImage.isEmpty()) {
            File oldImageFile = new File(uploadDirectory, oldImage);
            if (oldImageFile.exists()) {
                oldImageFile.delete();
            }
        }
         ProductDAO pdao = new ProductDAO();
        pdao.UpdateProduct(id, name, price, /*cateId,*/ descri, relativeImagePath, size);
    } else {
        // Xử lý khi không có file ảnh được chọn
    }
    
    response.sendRedirect("./ManageProduct?index=1");
}
  @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fol
}