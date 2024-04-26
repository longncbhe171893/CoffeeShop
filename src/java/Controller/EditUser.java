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
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
            // Kiểm tra email
            if (!isValidEmail(email)) {
                throw new IllegalArgumentException("Invalid email format");
            }

            // Kiểm tra số điện thoại
            if (!isValidPhoneNumber(phone)) {
                throw new IllegalArgumentException("Invalid phone number");
            }
            // Gửi email
            

            UserDAO udao = new UserDAO();
          if (udao.getUserByEmail(email) != null) {
                throw new IllegalArgumentException("Email already exists");
            }
            sendEmail(name, email, password, phone);
            // Cập nhật thông tin người dùng
             udao.updateUser(name, email, password, address, phone, sex, relativeImagePath, roleId, userpoint, id);
            response.sendRedirect("ManagerUser?index=1");

        }
    } catch (IllegalArgumentException e) {
        request.setAttribute("messregis", "Invalid data format. Please enter again.");
        response.sendRedirect("EditUser?userId=" + request.getParameter("id") + "&UserDetail=false");
    } catch (Exception e) {
        request.setAttribute("messregis", " ");
         response.sendRedirect("EditUser?userId=" + request.getParameter("id") + "&UserDetail=false");
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
            User user = userDAO.getUserById(Integer.valueOf(request.getParameter("userId")));
             ArrayList<Model.Setting> role = userDAO.getRole();
            if(request.getParameter("UserDetail").equals("true")){
                request.setAttribute("disable", "disabled");
            }
            String title = " > Edit User";
            String action = "EditUser";
            
            request.setAttribute("title", title);
            request.setAttribute("action", action);
            request.setAttribute("user", user);
            request.setAttribute("rlist", role);

            request.getRequestDispatcher("EditUser.jsp").forward(request, response);

        } catch (ServletException | IOException e) {

        }

    }
  private void sendEmail(String name, String email, String password,String phone) throws MessagingException {
        // Địa chỉ email của người nhận
        String to = email;

        // Địa chỉ email của người gửi
        String from = "manhld2k3@gmail.com";

        // Cài đặt thuộc tính hệ thống
        Properties properties = System.getProperties();

        // Cài đặt máy chủ SMTP
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Tạo một phiên làm việc với email
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication("lbao62258@gmail.com", "nxdm jgny bosb ibom"); // Thay thế bằng email và mật khẩu của bạn
            }
        });

        // Tạo một đối tượng MimeMessage
        MimeMessage mimeMessage = new MimeMessage(session);

        // Cài đặt địa chỉ email người gửi
        mimeMessage.setFrom(new InternetAddress(from));

        // Cài đặt địa chỉ email người nhận
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        // Cài đặt chủ đề email
        mimeMessage.setSubject("Message from admin");

        // Tạo nội dung email
        String emailContent = "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Password: " + password + "\n"
                + "Phone: " + phone + "\n";
        // Thiết lập nội dung email
        mimeMessage.setText(emailContent);

        // Gửi email
        Transport.send(mimeMessage);
        System.out.println("Đã gửi email thành công...");
    }

    // Kiểm tra số điện thoại có phải là dãy gồm 10 hoặc 11 số
    private boolean isValidPhoneNumber(String phone) {
        // Biểu thức chính quy kiểm tra số điện thoại có 10 hoặc 11 chữ số
        String regex = "^(\\+\\d{1,2})?(0|84)\\d{9,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    // Kiểm tra định dạng email
    private boolean isValidEmail(String email) {
        // Biểu thức chính quy kiểm tra định dạng email
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }  
} 
 
