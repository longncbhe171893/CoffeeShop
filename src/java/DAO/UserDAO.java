/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class UserDAO extends DBContext{
    
    private MD5 md5 = new MD5();
    
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
