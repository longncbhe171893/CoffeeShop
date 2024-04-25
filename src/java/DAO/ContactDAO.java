package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class ContactDAO extends DBContext {
//    public static void main(String[] args) {
//        ContactDAO contactDAO = new ContactDAO();
////        contactDAO.insertContact("a", "l@gmail.com", "0123456789", "a", "a", 8, 21);
//int a;
//a = contactDAO.getSetiing_id("Question");
//        System.out.println(a);
//    }
    
    public void insertContact(String name, String email, String phone, String subject, String message, int setting_id, int user_id) {

        String sql = "insert into `Contact` (`name`, `email`, `phone`, `subject`, `message`, `setting_id`, `user_id`, `status`) \n"
                + "values (?, ?, ?, ?, ?, ?, ?,1)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, subject);
            ps.setString(5, message);
            ps.setInt(6, setting_id);
            ps.setInt(7, user_id);
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getSetiing_id(String contact_type) {

        int setting_id = 0;
            if("Question".equals(contact_type)){
                setting_id = 8;
                if("Feedback".equals(contact_type)){
                        setting_id = 9;
                        }
                if ("Support".equals(contact_type)) {
                    setting_id = 10;
                }
                else{
                    setting_id = 11;
                }
            }
           return setting_id;
        
    }
}
