package DAO;

import Model.Contact;
import Model.Setting;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    public List<Contact> getAllContacts() {
        String query = "SELECT c.contact_id, c.name, c.email, c.phone, c.subject, c.message, c.setting_id, c.user_id, c.status, c.note, s.setting_name, s.type "
                + "FROM contact c "
                + "INNER JOIN setting s ON c.setting_id = s.setting_id;";
        List<Contact> contacts = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int contactId = resultSet.getInt("contact_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String subject = resultSet.getString("subject");
                String message = resultSet.getString("message");
                int settingId = resultSet.getInt("setting_id");
                int userId = resultSet.getInt("user_id");
                int status = resultSet.getInt("status");
                String note = resultSet.getString("note");
                String settingName = resultSet.getString("setting_name");
                String type = resultSet.getString("type");

                // Tạo một đối tượng Setting từ thông tin truy vấn
                Setting setting = new Setting();
                setting.setId(settingId);
                setting.setName(settingName);
                setting.setType(type);

                // Tạo đối tượng Contact với thông tin từ truy vấn và đối tượng Setting tương ứng
                Contact contact = new Contact();
                contact.setContact_id(contactId);
                contact.setName(name);
                contact.setEmail(email);
                contact.setPhone(phone);
                contact.setSubject(subject);
                contact.setMessage(message);
                contact.setSetting_id(settingId);
                contact.setUser_id(userId);
                contact.setStatus(status);
                contact.setNote(note);
                contact.setSetting(setting);

                // Thêm contact vào danh sách
                contacts.add(contact);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contacts;
    }
    
    public Contact getContactDetail(int contact_id) {
    String query = "SELECT c.contact_id, c.name, c.email, c.phone, c.subject, c.message, c.setting_id, c.user_id, c.status, c.note, s.setting_name, s.type "
                 + "FROM contact c "
                 + "INNER JOIN setting s ON c.setting_id = s.setting_id "
                 + "WHERE c.contact_id = ?";
    Contact contact = null;

    try {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, contact_id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int contactId = resultSet.getInt("contact_id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            String subject = resultSet.getString("subject");
            String message = resultSet.getString("message");
            int settingId = resultSet.getInt("setting_id");
            int userId = resultSet.getInt("user_id");
            int status = resultSet.getInt("status");
            String note = resultSet.getString("note");
            String settingName = resultSet.getString("setting_name");
            String type = resultSet.getString("type");

            // Tạo một đối tượng Setting từ thông tin truy vấn
            Setting setting = new Setting();
            setting.setId(settingId);
            setting.setName(settingName);
            setting.setType(type);

            // Tạo đối tượng Contact với thông tin từ truy vấn và đối tượng Setting tương ứng
            contact = new Contact();
            contact.setContact_id(contactId);
            contact.setName(name);
            contact.setEmail(email);
            contact.setPhone(phone);
            contact.setSubject(subject);
            contact.setMessage(message);
            contact.setSetting_id(settingId);
            contact.setUser_id(userId);
            contact.setStatus(status);
            contact.setNote(note);
            contact.setSetting(setting);
        }

        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return contact;
}
    public void updateContactNoteAndStatus(int status, String note, int contact_id) {
        String sql = " update `Contact` set `status`=?, `note`=? where `contact_id` =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setString(2, note);
            ps.setInt(3, contact_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Phương thức tìm kiếm liên hệ dựa trên subject


    // Phương thức tìm kiếm liên hệ dựa trên subject
    public List<Contact> searchContact(String search) {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT c.contact_id, c.name, c.email, c.phone, c.subject, c.message, c.setting_id, c.user_id, c.status, c.note, s.setting_name, s.type " +
                     "FROM contact c " +
                     "INNER JOIN setting s ON c.setting_id = s.setting_id " +
                     "WHERE c.subject LIKE ? " +
                     "ORDER BY c.contact_id ASC;";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int contactId = rs.getInt("contact_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String subject = rs.getString("subject");
                    String message = rs.getString("message");
                    int settingId = rs.getInt("setting_id");
                    int userId = rs.getInt("user_id");
                    int status = rs.getInt("status");
                    String note = rs.getString("note");
                    String settingName = rs.getString("setting_name");
                    String type = rs.getString("type");

                    // Tạo đối tượng Setting từ thông tin truy vấn
                    Setting setting = new Setting();
                    setting.setId(settingId);
                    setting.setName(settingName);
                    setting.setType(type);

                    // Tạo đối tượng Contact từ thông tin truy vấn và đối tượng Setting tương ứng
                    Contact contact = new Contact();
                    contact.setContact_id(contactId);
                    contact.setName(name);
                    contact.setEmail(email);
                    contact.setPhone(phone);
                    contact.setSubject(subject);
                    contact.setMessage(message);
                    contact.setSetting_id(settingId);
                    contact.setUser_id(userId);
                    contact.setStatus(status);
                    contact.setNote(note);
                    contact.setSetting(setting);

                    // Thêm contact vào danh sách
                    contacts.add(contact);
                }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }
    
    public ArrayList<Contact> filterContact(String filter) {
        String filterby = "";
        switch (filter) {
            case "1":
                filterby =  "WHERE s.setting_name ='Question'";
                break;
            case "2":
                filterby = "WHERE s.setting_name ='Feedback'";
                break;
            case "3":
                filterby = "WHERE s.setting_name ='Support'";
                break;
            default:
                filterby = "";
                break;

        }
        ArrayList<Contact> list = new ArrayList<>();
        String sql = " SELECT c.contact_id, c.name, c.email, c.phone, c.subject, c.message, c.setting_id, c.user_id, c.status, c.note, s.setting_name, s.type " +   
                      "FROM setting s " +
                      "INNER JOIN contact c ON s.setting_id = c.setting_id " + filterby;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int contactId = rs.getInt("contact_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String subject = rs.getString("subject");
                    String message = rs.getString("message");
                    int settingId = rs.getInt("setting_id");
                    int userId = rs.getInt("user_id");
                    int status = rs.getInt("status");
                    String note = rs.getString("note");
                    String settingName = rs.getString("setting_name");
                    String type = rs.getString("type");

                    // Tạo đối tượng Setting từ thông tin truy vấn
                    Setting setting = new Setting();
                    setting.setId(settingId);
                    setting.setName(settingName);
                    setting.setType(type);

                    // Tạo đối tượng Contact từ thông tin truy vấn và đối tượng Setting tương ứng
                    Contact contact = new Contact();
                    contact.setContact_id(contactId);
                    contact.setName(name);
                    contact.setEmail(email);
                    contact.setPhone(phone);
                    contact.setSubject(subject);
                    contact.setMessage(message);
                    contact.setSetting_id(settingId);
                    contact.setUser_id(userId);
                    contact.setStatus(status);
                    contact.setNote(note);
                    contact.setSetting(setting);
                    // Thêm contact vào danh sách
                    list.add(contact);
                        
            }
        } catch (SQLException e) {
        }
        return list;
    }
    
    public static void main(String[] args) {
        ContactDAO dao = new ContactDAO();
        dao.filterContact("1");
        System.out.print(dao.filterContact("1"));
    }
}


