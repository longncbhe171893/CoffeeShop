/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Role;
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

    public User getUserByEmail(String email) {
        String sql = "select * from `Users` where `email`= ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Setting r = new Setting(rs.getInt(9));
               User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),r,rs.getInt(10), rs.getDouble(11));
                return u;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

//    public void inserUser(String name, String email, String pass) {
//        String sql = "  insert into `Users` (`user_name`,`email`,`password`,`role_id`,`UserStatus_id`) \n"
//                + "  values (?,?,?,3,2)";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, name);
//            ps.setString(2, email);
//            ps.setString(3, md5.getMd5(pass));
//            ps.executeUpdate();
//        } catch (SQLException e) {
//        }
//    }
    public static void main(String[] args) {
        UserDAO ud = new UserDAO();
        ArrayList<User>  ls = ud.getAllUser();
        for (User l : ls) {
            System.out.println(l);
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
                    Setting r = new Setting(rs.getInt(9));
                    User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),r,rs.getInt(10), rs.getDouble(11));
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

//    public void UpdateUser(String name, int userid) {
//        String sql = " update `Users` set `user_name`=? where `user_id` =?";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, name);
//            ps.setInt(2, userid);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

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
    

    public ArrayList<User> SearchUser(String name, String srole) {
        ArrayList<User> list = new ArrayList<>();
        try {
            String sql = "SELECT u.user_id, u.user_name, u.email, u.password,u.address,u.phone,u.sex,u.user_image, u.user_status, u.user_point, s.*\n"
                    + "FROM `Users` u\n"
                    + "INNER JOIN `Setting` s ON u.`setting_id` = s.`setting_id`\n"
                    + "WHERE u.`user_name` LIKE ? OR s.`setting_name` LIKE ?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, "%" + name + "%");

            ps.setString(2, "%" + srole + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               Setting r = new Setting(rs.getInt("s.setting_id"), rs.getString("s.setting_name"));
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),r,rs.getInt(9), rs.getDouble(10)));
            }
        } catch (SQLException e) {

        }
        return list;
    }
    public ArrayList<User> getAllUsersToSetting() {
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
                        user.setRole(new Setting(resultSet.getString("setting_name")));
                        userList.add(user);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return userList;
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


    public void updateUserRole(int userId, int roleId) {
        try (
                 PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE `Users` SET `role_id` = ? WHERE `user_id` = ?"
                )) {

                    preparedStatement.setInt(1, roleId);
                    preparedStatement.setInt(2, userId);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
    }



}
