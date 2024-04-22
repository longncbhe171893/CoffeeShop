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
    

    public SettingDAO(Connection connection) {
        this.connection = connection;
    }

    public SettingDAO() {
    }
    
    

    public List<Setting> getAllSettings() {
        String query = "SELECT setting_id, setting_name, description, type, setting_sort, status FROM setting;";
        List<Setting> settings = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int settingId = resultSet.getInt("setting_id");
                String settingName = resultSet.getString("setting_name");
                String description = resultSet.getString("description");
                int settingSort = resultSet.getInt("setting_sort");
                String type = resultSet.getString("type");
                int status = resultSet.getInt("status");

                Setting setting = new Setting();
                setting.setId(settingId);
                setting.setName(settingName);
                setting.setDescription(description);
                setting.setType(type);
                setting.setSort(settingSort);
                setting.setStatus(status);
                settings.add(setting);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return settings;
    }
    
    public void addSetting(String setting_name, String description, String type, int status) {
        String sql = "INSERT INTO `Setting`\n"
                + "  (`setting_name`, `description`, `type`, `status`)\n"
                + "VALUES\n"
                + "  (?, ?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, setting_name);
            ps.setString(2, description);
            ps.setString(3, type);
            ps.setInt(4, status);
            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }
    
    public void UpdateStatusSetting(int status, int setting_id) {
        String sql = " update `Setting` set `status`=? where `setting_id` =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, setting_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateSetting(int id, String name, String description, String type, int status ) {
        String sql = " update `Setting` set `setting_name`=?, `description`=?, `type`=?, `status`=? where `setting_id` =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, type);
            ps.setInt(4, status);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Setting> searchSetting(String search) {

        List<Setting> list = new ArrayList<>();
        String sql = "SELECT `setting_id`, `setting_name`, `description`, `type`, `setting_sort`, `status`\n"
                + "FROM `Setting` \n WHERE `setting_name` LIKE ?\n"
                + "ORDER BY `setting_name` ASC;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int settingId = rs.getInt("setting_id");
                String settingName = rs.getString("setting_name");
                String description = rs.getString("description");
                int settingSort = rs.getInt("setting_sort");
                String type = rs.getString("type");
                int status = rs.getInt("status");

                Setting setting = new Setting();
                setting.setId(settingId);
                setting.setName(settingName);
                setting.setDescription(description);
                setting.setType(type);
                setting.setSort(settingSort);
                setting.setStatus(status);
                list.add(setting);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    
    public ArrayList<Setting> getAllSettingSort(String sort) {
        String sortby = "";
        switch (sort) {
            case "1":
                sortby = "order by setting_name asc";
                break;
            case "2":
                sortby = "order by type asc";
                break;
            case "3":
                sortby = "order by status asc";
                break;
            default:
                sortby = "order by setting_id asc";
                break;

        }
        ArrayList<Setting> list = new ArrayList<>();
        String sql = " select * from Setting "   + sortby;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int settingId = rs.getInt("setting_id");
                String settingName = rs.getString("setting_name");
                String description = rs.getString("description");
                int settingSort = rs.getInt("setting_sort");
                String type = rs.getString("type");
                int status = rs.getInt("status");

                Setting setting = new Setting();
                setting.setId(settingId);
                setting.setName(settingName);
                setting.setDescription(description);
                setting.setType(type);
                setting.setSort(settingSort);
                setting.setStatus(status);
                list.add(setting);
                        
            }
        } catch (SQLException e) {
        }
        return list;
    }
     // Phương thức để lấy danh sách các setting từ cơ sở dữ liệu
    public List<Setting> getSettings(int startIndex, int itemsPerPage) throws SQLException {
        List<Setting> settings = new ArrayList<>();
        String sql = "SELECT * FROM Setting LIMIT ?, ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, startIndex);
            statement.setInt(2, itemsPerPage);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("setting_name");
                    String description = rs.getString("description");
                    String type = rs.getString("type");
                    int status = rs.getInt("status");
                    Setting setting = new Setting(id, name, description, type, status);
                    settings.add(setting);
                }
            }
        }
        return settings;
    }

    // Phương thức để tính tổng số lượng setting trong cơ sở dữ liệu
    public int getTotalSettings() throws SQLException {
        int totalSettings = 0;
        String sql = "SELECT COUNT(*) AS total FROM Setting";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                totalSettings = rs.getInt("total");
            }
        }
        return totalSettings;
    }
    
    public static void main(String[] args) {
        SettingDAO dao = new SettingDAO();
        ArrayList<Setting> list = new ArrayList<>();
        dao.searchSetting("ad");
        
        System.out.println(dao);
    }


}

