/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author asus
 */
public class Contact extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * Xử lý phương thức HTTP <code>POST</code>.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException nếu có lỗi cụ thể của servlet xảy ra
     * @throws IOException nếu có lỗi I/O xảy ra
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String message = request.getParameter("message");

            // Kiểm tra email
            if (!isValidEmail(email) || !isValidPhoneNumber(phone)) {
                throw new IllegalArgumentException("Invalid email format");
            }

            // Gửi email
            sendEmail(name, email, phone, message);

            // Chuyển hướng sau khi gửi email thành công
            response.sendRedirect("Success.jsp");
        } catch (IllegalArgumentException e) {
            request.setAttribute("messregis", "Invalid data format. Please enter again.");
            request.getRequestDispatcher("Contact.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("messregis", " ");
            request.getRequestDispatcher("Contact.jsp").forward(request, response);
        }
    }

    /**
     * Trả về mô tả ngắn về servlet.
     *
     * @return một chuỗi chứa mô tả servlet
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

 
    private void sendEmail(String name, String email, String phone, String message) throws MessagingException {
        // Địa chỉ email của người nhận
        String to = "blong8444@gmail.com";

        // Địa chỉ email của người gửi
        String from = email;

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
        mimeMessage.setSubject("Message from user");

        // Tạo nội dung email
        String emailContent = "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Phone: " + phone + "\n"
                + "Message: " + message;

        // Thiết lập nội dung email
        mimeMessage.setText(emailContent);

        // Gửi email
        Transport.send(mimeMessage);
        System.out.println("Đã gửi email thành công...");
    }

    // Kiểm tra số điện thoại có phải là dãy gồm 10 hoặc 11 số
    private boolean isValidPhoneNumber(String phone) {
        // Biểu thức chính quy kiểm tra số điện thoại có 10 hoặc 11 chữ số
        String regex = "^(\\+?\\d{1,3})?\\s?(\\d{9,10})$";
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
