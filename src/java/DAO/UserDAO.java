/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Role;
import Model.Setting;
import Model.User;
import Model.UserStatus;
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

    public static void main(String[] args) {
        UserDAO ud = new UserDAO();
        User ls = ud.getUserByEmail("abc@gmail.com");
//        for (User l : ls) {
//            System.out.println(l);
//        }
        System.out.println(ls);
        User a = new User(0, "name", "email", "password", "address", 0, "sex", "image", 3, 0, 0);
        ud.inserUser(a.getName(), a.getEmail(), a.getPassword());
    }

    public User getUserByEmail(String email) {
        String sql = "select * from `Users` where `email`= ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                User user = new User(rs.getInt("user_id"), rs.getString("user_name"),
                        rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getInt("phone"), rs.getString("sex"),
                        rs.getString("user_image"), rs.getInt("setting_id"), rs.getInt("user_status"), rs.getDouble("user_point"));
                return user;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    ////        private int id;
//    private String name;
//    private String email;
//    private String password;
//    private String address;
//    private int phone;
//    private String sex;
//    private String image;
//    private int setting_id;
//    private int status;
//    private double point; 

    public void inserUser(String name, String email, String pass) {
//        String sql = "  insert into `Users` (`user_name`,`email`,`password`,`address`,`phone`,`sex`,`setting_id`,`user_status`,`user_point`) \n"
//                + "  values (?,?,?,3,2)";
        String sql = "insert into `Users` (`user_name`, `email`, `password`, `setting_id`, `user_status`, `user_point`) \n"
                + "values (?, ?, ?, 3, 1, 0)";

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

                    UserStatus st = new UserStatus(rs.getInt(6));
                    User user = new User(rs.getInt("user_id"), rs.getString("user_name"),
                            rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getInt("phone"), rs.getString("sex"),
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
        String sql = "update `Users` set `UserStatus_id` = ? where `email` = ?";
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

    public ArrayList<User> SearchUser(String name, String srole) {
        ArrayList<User> list = new ArrayList<>();
        try {
            String sql = "SELECT u.user_id, u.user_name, u.email, u.password, u.UserStatus_id, u.user_point, r.*\n"
                    + "FROM `Users` u\n"
                    + "INNER JOIN `Roles` r ON u.`role_id` = r.`role_id`\n"
                    + "WHERE u.`user_name` LIKE ? OR r.`role_name` LIKE ?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, "%" + name + "%");

            ps.setString(2, "%" + srole + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role r = new Role(rs.getInt(7), rs.getString(8));
                UserStatus st = new UserStatus(rs.getInt(5));
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), r, st, rs.getDouble(6)));
            }
        } catch (SQLException e) {

        }
        return list;
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
