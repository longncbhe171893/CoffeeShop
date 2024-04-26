/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Slider;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SliderDAO extends DBContext {
    public ArrayList<Slider> getAllSlider() {
        ArrayList<Slider> sliderList = new ArrayList<>();
        String sql = "SELECT * FROM swp391.slider";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                sliderList.add(new Slider(
                        rs.getInt("slider_id"),
                        rs.getString("title"),
                        rs.getString("img"),
                        rs.getString("url"),
                        rs.getInt("status")
                        ));

            }
        } catch (SQLException e) {
        }

        return sliderList;
    }
//    public List<Slider> searchSlider(String search) {
//        List<Slider> sliders = new ArrayList<>();
//        String sql = "SELECT s.slider_id, s.title, s.img, s.url,  s.status " +
//                     "FROM slider s " +
//                     
//                     "WHERE s.status LIKE ? " +
//                     "ORDER BY s.status ASC;";
//        try{
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, "%" + search + "%");
//            ResultSet rs = ps.executeQuery();
//                while (rs.next()) {
//                    int slider_id = rs.getInt("slider_id");
//                    String title = rs.getString("title");
//                    String img = rs.getString("img");
//                    String url = rs.getString("url");
//                   
//                    int status = rs.getInt("status");
//                   
//                    // Tạo đối tượng Contact từ thông tin truy vấn và đối tượng Setting tương ứng
//                    Slider slider = new Slider();
//                    slider.setSlider_id(slider_id);
//                    slider.setTitle(title);
//                    slider.setImg(img);
//                    slider.setUrl(url);
//                    slider.setStatus(status);
//                    // Thêm contact vào danh sách
//                    sliders.add(slider);
//                }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sliders;
//    }
    
    public List<Slider> getSliderWithStatusFilter(int status, int offset, int recordsPerPage) {
    List<Slider> sliders = new ArrayList<>();
    try {
        ArrayList<Slider> sliderList = new ArrayList<>();
        
        String sql = "SELECT * FROM swp391.slider WHERE `status` = ? LIMIT ?, ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, status);
        ps.setInt(2, offset);
        ps.setInt(3, recordsPerPage);

        // Execute the query and iterate through the results
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Slider slider = new Slider(
                    rs.getInt("slider_id"),
                    rs.getString("title"),
                    rs.getString("img"),
                    rs.getString("url"),
                    rs.getInt("status")
            );
            sliders.add(slider);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return sliders;
}
    
    public void addSlider(String title, String imagePath) {
    String sql = "INSERT INTO `Slider` (`title`, `img`, `status`)\n"
                + " VALUES (?, ?, 1)";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, imagePath);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        // Xử lý exception tại đây nếu cần thiết
    }
}

    
    
    public ArrayList<Slider> searchSlider(String search) {
        ArrayList<Slider> list = new ArrayList<>();
        String sql = " SELECT *\n"
                + "FROM `Slider` s\n"
                + "WHERE s.`title` LIKE ? \n"
                + "ORDER BY s.`slider_id` ASC;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Slider(
                        rs.getInt("slider_id"),
                        rs.getString("title"),
                        rs.getString("img"),
                        rs.getString("url"),
                        rs.getInt("status")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public void UpdateStatusSlider(int status, int id) {
        String sql = " update `Slider` set `status`=? where `slider_id` =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<Slider> getSliderWithPagination(int offset, int recordsPerPage) {
        ArrayList<Slider> list = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT s.slider_id, s.title, s.img,s.url, s.status FROM `slider` s WHERE s.status IN (1, 2) ORDER BY s.`status` ASC LIMIT ?, ?;";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, offset);
            ps.setInt(2, recordsPerPage);
            rs = ps.executeQuery();

            while (rs.next()) {
                Slider slider = new Slider(rs.getInt(1), rs.getString(2), rs.getString("img"),rs.getString("url"), rs.getInt("status"));
                list.add(slider);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public int getTotalSliderCount() {
        int total = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String query = "SELECT COUNT(*) AS total FROM `slider` WHERE `status` IN (1, 2)";
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return total;
    }
    
    public void updateSliderWithImage(String title, String imagePath,String url, String id, String status) {
        String sql = "UPDATE Slider SET title=?, img=?, status=?, url=? WHERE slider_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, imagePath);
            ps.setString(3, status);
            ps.setString(4, url);
            ps.setString(5, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý exception tại đây nếu cần thiết
        }
    }
    public void updateSliderWithoutImage(String title, String url, String id, String status) {
        String sql = "UPDATE Slider SET title=?, status=?, url=? WHERE slider_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, status);
            ps.setString(3, url);
            ps.setString(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý exception tại đây nếu cần thiết
        }
    }
    public static void main(String[] args) {
        SliderDAO slider = new SliderDAO();
//        List<Slider> listP = slider.getAllSlider();
//        System.out.println(listP);
        String title = "Slider Title";
    String imagePath = "./ProductDetails?pid=2"; 
    slider.addSlider(title, imagePath);
    }
    
    
    
}
