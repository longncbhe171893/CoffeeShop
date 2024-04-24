/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 * @author HP
 */
public class ContactDAO extends DBContext {

    
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

}
