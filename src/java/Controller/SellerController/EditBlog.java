/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.SellerController;

import DAO.BlogDao;
import Model.Blog;
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
public class EditBlog extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        try {
<<<<<<< HEAD
        String index = request.getParameter("index");
        String title = request.getParameter("title");
        String img = request.getParameter("img");
        Part imagePart = request.getPart("img");
=======

        String title = request.getParameter("title");
        String img = request.getParameter("img");
        Part imagePart = request.getPart("img");
        int index = Integer.valueOf(request.getParameter("index"));
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
//        int userId = Integer.valueOf(request.getParameter("user"));
        int userId = 8;
        String content = request.getParameter("content");
        int setting_id = Integer.parseInt(request.getParameter("category"));
        String shortDescription = request.getParameter("short_description");
        int idBlog = Integer.valueOf(request.getParameter("blogId"));
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
            BlogDao bdao = new BlogDao();
            bdao.updateBlog(idBlog, title, relativeImagePath, userId, content, setting_id, shortDescription);
<<<<<<< HEAD

            response.sendRedirect("ManageBlog?index=" + index);
=======
            response.sendRedirect("ManageBlog?index="+index);
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
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
<<<<<<< HEAD

=======
            int index = Integer.valueOf(request.getParameter("index"));
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
            BlogDao blogDao = new BlogDao();
            List<Model.Category> category = blogDao.getcategoryBlogByType();
            Blog blog = blogDao.getBlogByBlogId(Integer.valueOf(request.getParameter("blogId")));
            if (request.getParameter("BlogDetail").equals("true")) {
                request.setAttribute("disable", "disabled");
<<<<<<< HEAD
                request.setAttribute("hidden", "hidden");
            }
            String index = request.getParameter("index");
=======
            }
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
            String title = " > Edit Blog";
            String action = "EditBlog";

            request.setAttribute("title", title);
            request.setAttribute("action", action);
            request.setAttribute("blog", blog);
<<<<<<< HEAD
            request.setAttribute("index", index);

            request.setAttribute("categoryBlog", category);

=======
            request.setAttribute("categoryBlog", category);
            request.setAttribute("index", index);
>>>>>>> ca4c3917bbc31530b5e50fd946e0f1df5a7de7e2
            request.getRequestDispatcher("EditBlog.jsp").forward(request, response);

        } catch (ServletException | IOException e) {

        }

    }
}
