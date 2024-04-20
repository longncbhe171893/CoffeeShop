/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.setting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class SettingDAO extends DBContext {
    

    public SettingDAO(Connection connection) {
        this.connection = connection;
    }

    public SettingDAO() {
    }
    
    

    public List<setting> getAllSettings() {
        String query = "SELECT setting_id, setting_name, description, type FROM setting;";
        List<setting> settings = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int settingId = resultSet.getInt("setting_id");
                String settingName = resultSet.getString("setting_name");
                String description = resultSet.getString("description");
                String type = resultSet.getString("type");

                setting setting = new setting(settingId, settingName, description, type);
                settings.add(setting);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return settings;
    }
    
    public static void main(String[] args) {
        SettingDAO dao = new SettingDAO();
        System.out.println(dao.getAllSettings());
    }


}

