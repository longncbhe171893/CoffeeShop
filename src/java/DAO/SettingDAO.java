/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Setting;
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
    private Connection connection;

    public SettingDAO(Connection connection) {
        this.connection = connection;
    }

    public SettingDAO() {
    }
    
    

    public List<Setting> getAllSettings() throws SQLException {
        List<Setting> settings = new ArrayList<>();
        String query = "SELECT * FROM setting";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Setting setting = new Setting();
                setting.setSettingId(resultSet.getInt("setting_id"));
                setting.setSettingName(resultSet.getString("setting_name"));
                setting.setDescription(resultSet.getString("description"));
                setting.setType(resultSet.getString("type"));
                setting.setSettingSort(resultSet.getInt("setting_sort"));
                settings.add(setting);
            }
        }
        return settings;
    }
}

