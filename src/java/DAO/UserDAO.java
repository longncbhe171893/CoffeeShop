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

/**
 *
 * @author asus
 */
public class UserDAO extends DBContext {

    private MD5 md5 = new MD5();

//    public static void main(String[] args) {
//        UserDAO ud = new UserDAO();
////        User ls = ud.getUserByEmail("abc@gmail.com");
////        System.out.println(ls);
////        User a = new User(0, "name", "email", "password", "address", null , "sex", "image", 3, 0, 0);
////        ud.inserUser(a.getName(), a.getEmail(), a.getPassword());
////            ArrayList<User> ls= ud.getAllUser();
////            for (User l : ls) {
////            System.out.println(l);
////    }
//           ud.changePasswordByEmail("LongNCBHE171893@fpt.edu.vn", "12345678");
//}
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

    public void inserUser(String name, String email, String pass) {
//        String sql = "  insert into `Users` (`user_name`,`email`,`password`,`address`,`phone`,`sex`,`setting_id`,`user_status`,`user_point`) \n"
//                + "  values (?,?,?,3,2)";
        String sql = "insert into `Users` (`user_name`, `email`, `password`, `setting_id`, `user_status`, `user_point`) \n"
                + "values (?, ?, ?, 3, 0, 0)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, md5.getMd5(pass));
            ps.executeUpdate();
        } catch (SQLException e) {
        }
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

    public void updateUserbyStatus(String email, int stId) {
        String sql = "update `Users` set `user_status` = ? where `email` = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, stId);
            stm.setString(2, email);
            stm.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void UpdateUser(String name, int userid) {
        String sql = " update `Users` set `user_name`=? where `user_id` =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, userid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void UpdateStatusUser(int sid, int uid) {
        String sql = " update `Users` set `UserStatus_id`=? where `user_id` =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sid);
            ps.setInt(2, uid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changePassword(String id, String password) {
        String sql = "update `Users` set `password`=? where `user_id` =?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, md5.getMd5(password));
            stm.setString(2, id);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void changePasswordByEmail(String email, String pass) {
        String sql = " update `Users` set `password`=? where `email` =?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, md5.getMd5(pass));
            stm.setString(2, email);
            stm.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public ArrayList<User> searchUser(String nameOrrole) {
        ArrayList<User> list = new ArrayList<>();
        String sql;
        int settingid;
        PreparedStatement ps;
        try {
            if (getSettingidbyString(nameOrrole) != -1) {
                settingid = getSettingidbyString(nameOrrole);
                sql = "SELECT * FROM users WHERE setting_id = ?";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, settingid);
            } else {
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
            settingid = rs.getInt(1);

            return settingid;
        } catch (SQLException e) {
            return -1;
        }
    }
    
    public void UpdateUser(String name, int userid, String sex, String phone, String address) {
        String sql = "UPDATE `Users` SET `user_name`=?, `sex`=?, `phone`=?, `address`=? WHERE `user_id`=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, sex);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setInt(5, userid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
