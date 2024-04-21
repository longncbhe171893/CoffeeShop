/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Setting;
import Model.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author asus
 */
public class UserDAO extends DBContext {

    private MD5 md5 = new MD5();

    public static void main(String[] args) {
UserDAO userDAO = new UserDAO();
        
        // Số lượng bản ghi muốn hiển thị trên mỗi trang
      ArrayList<User> userList = userDAO.pagingUser(1, 4);
        
// In ra thông tin của các người dùng trong danh sách
for (User user : userList) {
    System.out.println("User ID: " + user.getId());
    System.out.println("User Name: " + user.getName());
    System.out.println("Email: " + user.getEmail());
    System.out.println("Password: " + user.getPassword());
    System.out.println("Address: " + user.getAddress());
    System.out.println("Phone: " + user.getPhone());
    System.out.println("Sex: " + user.getSex());
    System.out.println("Image: " + user.getImage());
    System.out.println("Setting ID: " + user.getSetting_id());
    System.out.println("User Status: " + user.getUserStatus());
    System.out.println("User Point: " + user.getPoint());
    System.out.println("------------------------------------");
    }
    }

    public ArrayList<User> pagingUser(int index, int numOrOnPage) {
        ArrayList<User> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `Users` order by user_status asc LIMIT ?, ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            index = (index - 1) * numOrOnPage;
            ps.setInt(1, index);
            ps.setInt(2, numOrOnPage);
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                 list.add(new User(
                rs.getInt("user_id"), rs.getString("user_name"),
                            rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getString("phone"), rs.getInt("sex"),
                            rs.getString("user_image"), rs.getInt("setting_id"), rs.getInt("user_status"), rs.getDouble("user_point")));
            }
        } catch (SQLException e) {

        }
        return list;
    }
     public int countUser() {
        int count;
        try {
            String sql = " select count(*) from `Users`";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
            return count;

        } catch (SQLException e) {

        }
        return 0;
    }
    public User getUserByEmail(String email) {
        String sql = "select * from `Users` where `email`= ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                User user = new User(rs.getInt("user_id"), rs.getString("user_name"),
                        rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getString("phone"), rs.getInt("sex"),
                        rs.getString("user_image"), rs.getInt("setting_id"), rs.getInt("user_status"), rs.getDouble("user_point"));
                return user;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
      public User getUserById(int id) {
        String sql = "select * from `Users` where `user_id`= ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                User user = new User(rs.getInt("user_id"), rs.getString("user_name"),
                        rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getString("phone"), rs.getInt("sex"),
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
    public void UpdateUser(String name, int userid, int sex, String phone, String address) {
        String sql = "UPDATE `Users` SET `user_name`=?, `sex`=?, `phone`=?, `address`=? WHERE `user_id`=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, sex);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setInt(5, userid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
                            rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getString("phone"), rs.getInt("sex"),
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
                        rs.getInt(7),
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

    public void addUser(String name, String email, String password, String address, String phone, int sex,String image, double userpoint) {
        String sql = "INSERT INTO `Users`\n"
                + "  (`user_name`, `email`, `password`, `address`, `phone`,`user_image`, `sex`,`setting_id`,`user_status`, `user_point`)\n"
                + "VALUES\n"
                + "  (?, ?, ?, ?, ?, ?,?,3,1, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, md5.getMd5(password));
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.setInt(6, sex);
            ps.setString(7, image);
            ps.setDouble(8, userpoint);
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
public void updateUser(String name, String email, String password, String address, String phone, int sex,String image, double userpoint,int id) {
        String sql = "UPDATE `Users`\n"
                + "SET `user_name` = ?, `email` = ?, `password` = ?, `address` = ?,`phone`= ?,`sex`=?,`user_image`=?,`user_point`=?\n"
                + "WHERE `user_id` = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.setInt(6, sex);
            ps.setString(7, image);
            ps.setDouble(8, userpoint);
            ps.setInt(9, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    } 
}
