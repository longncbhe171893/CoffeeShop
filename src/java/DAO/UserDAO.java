/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Setting;
import Model.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author asus
 */
public class UserDAO extends DBContext {

    private MD5 md5 = new MD5();

    public static void main(String[] args) {
        UserDAO ud = new UserDAO();
  ud.updateUser("John Doe", "john@example.com", "newpassword123", "456 Oak St", "987654321", 2, "new_user_image.jpg", 2, 200.0, 35);

            System.out.println("User updated successfully.");
}
    public void updateUser(String name, String email, String password, String address, String phone, int sex, String image,int roleId, double userpoint, int id) {
         String sql = "UPDATE `Users` SET `user_name`=?, `email`=?,`password`=?, `address`=?,`phone`=?,`sex`=?, `user_image`=?,`setting_id`=?,`user_point`=? WHERE `user_id`=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2,email);
            ps.setString(3, md5.getMd5(password));
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.setInt(6, sex);
            ps.setString(7, image);
            ps.setInt(8, roleId);
            ps.setDouble(9, userpoint);
            ps.setInt(10, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
public void addUser(String name, String email, String password, String address, String phone, int sex,String image,int roleId, double userpoint) {
   
        String sql = "INSERT INTO `Users`\n"
                + "  (`user_name`, `email`, `password`, `address`, `phone`, `sex`,`user_image`,`setting_id`,`user_status`, `user_point`)\n"
                + "VALUES\n"
                + "  (?, ?, ?, ?, ?, ?,?,?,1, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2,email);
            ps.setString(3, md5.getMd5(password));
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.setInt(6, sex);
            ps.setString(7, image);
            ps.setInt(8, roleId);
            ps.setDouble(9, userpoint);
            ps.executeUpdate();
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu cần
    }
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
         private Setting getSettingById(int aInt) {

        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM swp391.setting where setting_id = ?;";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, aInt);
            rs = ps.executeQuery();
            rs.next();
            return new Setting(rs.getInt(1), rs.getString(2), rs.getString(4));

        } catch (SQLException e) {

        }
        return null;
    }
       public ArrayList<User> pagingUser(int index, int numOrOnPage) {
        ArrayList<User> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `Users` LIMIT ?, ?;";
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
 public ArrayList<Setting> getRole() {
        ArrayList<Setting> list = new ArrayList<>();
        String sql = "  select `setting_id`,`setting_name` from `Setting` where `type`='User' and `setting_name` not like 'Admin'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Setting(rs.getInt("setting_id"), rs.getString("setting_name")));
            }
        } catch (Exception e) {
        }
        return list;
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
                        rs.getInt("setting_id"),
                        rs.getInt(10),
                        rs.getDouble(11)
                ));
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ nếu cần
        }
        return list;
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
    
    public void UpdateUser(String name, int userid, int sex, String phone, String address, String image) {
        String sql = "UPDATE `Users` SET `user_name`=?, `sex`=?, `phone`=?, `address`=?, `user_image`=? WHERE `user_id`=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, sex);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, image);
            ps.setInt(6, userid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean checkPhonenumber(String phone) {
        String regex = "^\\d{10}$";
        Pattern p = Pattern.compile(regex);
        if (phone == null) {
            return false;
        }

        Matcher m = p.matcher(phone);
        return m.matches();
    }

    //  Kiểm tra password nhập vào có đúng điều kiện không, điều kiên 
    //  cụ thể ở đây là password phải có từ 6 đến 20 kí tự bao gồm chữ và số.
    public boolean checkPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z]).{6,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }

        Matcher m = p.matcher(password);
        return m.matches();
    }
    
    public String encodeImage(String imagePath) throws IOException {
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            return Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found: " + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return null;
    }
    
    
}
