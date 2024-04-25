/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.ArrayList;

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
public class AddProduct extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String title = " > Add Product";
            String action = "AddProduct";
            ProductDAO product = new ProductDAO();
             ArrayList<Model.Setting> category = product.GetCategory();
            request.setAttribute("title", title);
            request.setAttribute("action", action);
            request.setAttribute("clist", category);
            request.getRequestDispatcher("EditProduct.jsp").forward(request, response);

        } catch (ServletException | IOException e) {

        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name"); 
        double price = Double.valueOf(request.getParameter("price"));
        int cateId = Integer.valueOf(request.getParameter("category"));
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
            String descri = request.getParameter("descri");
            // Tiếp tục xử lý và lưu thông tin từ form vào cơ sở dữ liệu

            ProductDAO pdao = new ProductDAO();
            pdao.AddProduct(name, price, cateId, relativeImagePath, descri);
        } else {
        }
        response.sendRedirect("./ManageProduct?index=1");
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
