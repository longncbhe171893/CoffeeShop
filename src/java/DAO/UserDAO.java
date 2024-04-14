/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Setting;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 *
 */
public class UserDAO extends DBContext {

    private MD5 md5 = new MD5();

    public static void main(String[] args) {
        UserDAO ud = new UserDAO();
             ud.addUser("abc", "abc@gmail.com", "abc", null, null, null, 0.0);
       
      
    }

    public User getUserByEmail(String email) {
        String sql = "select * from `Users` where `email`= ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                User user = new User(rs.getInt("user_id"), rs.getString("user_name"),
                        rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getString("phone"), rs.getString("sex"),
                        rs.getString("user_image"), rs.getInt("setting_id"), rs.getInt("user_status"), rs.getDouble("user_point"));
                return user;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> listUser = new ArrayList<>();
        String sql = "select * from `Users`";
        try {
            ResultSet rs;
            try ( PreparedStatement ps = connection.prepareStatement(sql)) {
                rs = ps.executeQuery();
                while (rs.next()) {
                    User user = new User(rs.getInt("user_id"), rs.getString("user_name"),
                            rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getString("phone"), rs.getString("sex"),
                            rs.getString("user_image"), rs.getInt("setting_id"), rs.getInt("user_status"), rs.getDouble("user_point"));
                    listUser.add(user);
                }

            }
            return listUser;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<User> searchUser(String nameOrrole) {
    ArrayList<User> list = new ArrayList<>();
    String sql;
    int settingid;
    PreparedStatement ps;
    try {
        if(getSettingidbyString(nameOrrole)!= -1){
     settingid = getSettingidbyString(nameOrrole);
            sql = "SELECT * FROM users WHERE setting_id = ?";
             ps = connection.prepareStatement(sql);
            ps.setInt(1,settingid);
    }
    else{
         sql = "SELECT * FROM users WHERE user_name LIKE ?";
           ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + nameOrrole + "%");
    }
       
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new User(
                rs.getInt(1), 
                rs.getString(2), 
                rs.getString(3), 
                rs.getString(4), 
                rs.getString(5), 
                rs.getString(6), 
                rs.getString(7), 
                rs.getString(8), 
                rs.getInt(9), 
                rs.getInt(10), 
                rs.getDouble(11)
            ));
        }
    } catch (SQLException e) {
        // Xử lý ngoại lệ nếu cần
    }
    return list;
}
    /*public ArrayList<User> getAllUsersToSetting() {
        ArrayList<User> userList = new ArrayList<>();

        try (
                 PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT u.user_id, u.user_name, u.email, s.setting_name\n"
                        + "FROM `Users` u\n"
                        + "JOIN `Setting` s ON u.`setting_id` = s.`setting_id`\n"
                        + "WHERE u.`setting_id` = 2 OR u.`setting_id` = 3;"
                );  ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        User user = new User();
                        user.setId(resultSet.getInt("user_id"));
                        user.setName(resultSet.getString("user_name"));
                        user.setEmail(resultSet.getString("email"));
                        user.setSetting_id(newresultSet.getString("setting_name")));
                        userList.add(user);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return userList;
    }
*/
    public void UpdateStatusUser(int sid, int uid) {
        String sql = " update `Users` set `user_status`=? where `user_id` =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sid);
            ps.setInt(2, uid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addUser(String name, String email, String password, String address, String phone, String sex, double userpoint) {
        String sql = "INSERT INTO `Users`\n"
                + "  (`user_name`, `email`, `password`, `address`, `phone`, `sex`,`setting_id`,`user_status`, `user_point`)\n"
                + "VALUES\n"
                + "  (?, ?, ?, ?, ?, ?,3,1, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, md5.getMd5(password));
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.setString(6, sex);
            ps.setDouble(7, userpoint);
            ps.executeUpdate();
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu cần
        }
    }

    private int getSettingidbyString(String srole) {
        String sql = "select * from `Setting` where `setting_name`like ?";
        int settingid;
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, srole);
            ResultSet rs = ps.executeQuery();
            rs.next();
            settingid=rs.getInt(1);
            
           return settingid;
        } catch (SQLException e) {
           return -1;
        }
    }
}
