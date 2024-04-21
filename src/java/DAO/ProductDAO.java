/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Setting;
import Model.Product;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProductDAO extends DBContext {

    public static void main(String[] args) {
       ProductDAO productDAO = new ProductDAO();
        
        // Gọi phương thức getAllProducts để lấy danh sách sản phẩm
        ArrayList<Product> productList = productDAO.pagingProduct(1, 4);
        
        // In ra thông tin của các sản phẩm trong danh sách
        for (Product product : productList) {
            System.out.println("Product ID: " + product.getId());
            System.out.println("Product Name: " + product.getName());
            System.out.println("Product Price: " + product.getPrice());
            System.out.println("Setting ID: " + product.getSetting_id());
            System.out.println("Image: " + product.getImage());
            System.out.println("Description: " + product.getDecription());
            System.out.println("Product Status: " + product.getProductStatus());
            System.out.println("Create Date: " + product.getCreateDate());
            System.out.println("Size: " + product.getSize());
            System.out.println("------------------------------------");
        }
    
    }
    public ArrayList<Product> pagingProduct(int index, int numOrOnPage) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `Product` order by product_status asc LIMIT ?, ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            index = (index - 1) * numOrOnPage;
            ps.setInt(1, index);
            ps.setInt(2, numOrOnPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 list.add(new Product(
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getDouble("product_price"),
                rs.getInt("setting_id"),
                rs.getString("img"),
                rs.getString("description"),
                rs.getInt("product_status"),
                rs.getDate("create_date"),
                rs.getInt("size")));
            }
        } catch (SQLException e) {

        }
        return list;
    }
     public int countProduct() {
        int count;
        try {
            String sql = " select count(*) from `Product`";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
            return count;

        } catch (SQLException e) {

        }
        return 0;
    }
      public ArrayList<Product> getProduct(String search, int index, String sort) {
    String sortby ="";
    switch (sort) {
        case "1":
            sortby = " p.`create_date` DESC";
            break;
        case "2":
            sortby = " p.`product_price` ASC";
            break;
        case "3":
            sortby = " p.`product_price` DESC";
            break;
        default:
            sortby = " p.`product_name` DESC";
            break;
    }
    ArrayList<Product> list = new ArrayList<>();
    String sql = " SELECT * FROM `Product` p " +
    "JOIN `Setting` s ON p.`setting_id` = s.`setting_id`\n"+
                 " WHERE p.`product_status` = 1 " +
                 " AND p.`product_name` LIKE ? " +
                 " ORDER BY "+sortby+ " LIMIT ?, 8;";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + search + "%");
        ps.setInt(2, (index - 1) * 6);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
             
            list.add(new Product(
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getDouble("product_price"),
                rs.getInt("setting_id"),
                rs.getString("img"),
                rs.getString("description"),
                rs.getInt("product_status"),
                rs.getDate("create_date"),
                rs.getInt("size")));
        }
    } catch (SQLException e) {
        // Xử lý ngoại lệ nếu có
    }
    return list;
}

    public ArrayList<Product> getTopSelling() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT p.*\n"
                + "FROM `Product` p\n"
                + "JOIN (\n"
                + "  SELECT SUM(quantity) AS numberSell, product_id\n"
                + "  FROM `OrderDetail`\n"
                + "  GROUP BY product_id\n"
                + ") AS b ON b.product_id = p.product_id\n"
                + "ORDER BY b.numberSell DESC\n"
                + "LIMIT 8;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getDouble("product_price"),
                rs.getInt("setting_id"),
                rs.getString("img"),
                rs.getString("description"),
                rs.getInt("product_status"),
                rs.getDate("create_date"),
                rs.getInt("size")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Product> getAllProduct(String cid, String search, int index, String sort) {
        String sortby = "";
        switch (sort) {
            case "1":
                sortby = "order by p.`create_date` desc";
                break;
            case "2":
                sortby = "order by p.`product_price` asc";
                break;
            case "3":
                sortby = "order by p.`product_price` desc";
                break;
            default:
                sortby = "order by p.`product_name` desc";
                break;

        }
        ArrayList<Product> list = new ArrayList<>();
        String sql = " SELECT *\n"
                + "FROM `Product` p\n"
                + "WHERE p.`product_status` = 1\n"
                + "  AND p.`setting_id` = ?\n"
                + "  AND p.`product_name` LIKE ?\n"
                +sortby+"\n"
                + "LIMIT ?, 9;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + cid + "%");
            ps.setString(2, "%" + search + "%");
            ps.setInt(3, (index - 1) * 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getDouble("product_price"),
                rs.getInt("setting_id"),
                rs.getString("img"),
                rs.getString("description"),
                rs.getInt("product_status"),
                rs.getDate("create_date"),
                rs.getInt("size")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public Product getProductById(int pid) {
        String sql = "SELECT *\n"
                + "FROM `Product` p\n"
                + "WHERE p.`product_id` = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getDouble("product_price"),
                rs.getInt("setting_id"),
                rs.getString("img"),
                rs.getString("description"),
                rs.getInt("product_status"),
                rs.getDate("create_date"),
                rs.getInt("size"));
                return p;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<Setting> getCategory() {
        ArrayList<Setting> list = new ArrayList<>();
        String sql = "  select `setting_id` from `Setting`";
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

    public int getNumberProduct(String cid, String search) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "  select count(*) from `Product` p  \n"
                + "  where p.`setting_id` like ?  and p.`product_name` like ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + cid + "%");
            ps.setString(2, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }


    public void AddProduct(String name, double price, int cateId, String descri, String img,int size) {
        String sql = "INSERT INTO `Product`\n"
                + "  (`product_name`, `product_price`, `product_status`, `setting_id`, `img`, `description`, `create_date`,`size`)\n"
                + "VALUES\n"
                + "  (?, ?, 1, ?, ?, ?, NOW(),?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, cateId);
            ps.setString(4, descri);
            ps.setString(5, img);
            ps.setInt(6, size);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void UpdateProduct(int id, String name, double price, int cateId, String descri, String img,int size) {
        String sql = "UPDATE `Product`\n"
                + "SET `product_name` = ?, `product_price` = ?, `setitng_id` = ?,\n"
                + "    `img` = ?, `description` = ?, `create_date` = NOW(),`size`= ?\n"
                + "WHERE `product_id` = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, cateId);
            ps.setString(4, descri);
            ps.setString(5, img);
            ps.setInt(6, size);
            ps.executeUpdate();
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void UpdateProductStatus(int psId, int pId) {
        String sql = "update `Product` set `product_status` = ? where `product_id` = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, psId);
            ps.setInt(2, pId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public ArrayList<Product> getAllProducts() {
    ArrayList<Product> list = new ArrayList<>();
    String sql = "SELECT * FROM `Product` ORDER BY `product_id` ASC;";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Product(
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getDouble("product_price"),
                rs.getInt("setting_id"),
                rs.getString("img"),
                rs.getString("description"),
                rs.getInt("product_status"),
                rs.getDate("create_date"),
                rs.getInt("size")));
        }
    } catch (Exception e) {
        e.printStackTrace(); // In ra lỗi nếu có
    }
    return list;
}
/*
    public ProductSize getProductSizeByID(int id) {
        try {
            String sql = "SELECT * FROM `ProductSize` WHERE `productSize_id` = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSize productSize = new ProductSize(rs.getInt(1), rs.getString(2), rs.getDouble(3));
                return productSize;
            }
        } catch (SQLException e) {

        }
        return null;

    }
*/
    public ArrayList<Product> searchProduct(String search) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = " SELECT *\n"
                + "FROM `Product` p\n"
                + "WHERE p.`product_name` LIKE ?\n"
                + "ORDER BY p.`product_id` ASC;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {           
                list.add(new Product(
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getDouble("product_price"),
                rs.getInt("setting_id"),
                rs.getString("img"),
                rs.getString("description"),
                rs.getInt("product_status"),
                rs.getDate("create_date"),
                rs.getInt("size")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Product> getProductByDate(Date fdate, Date sdate) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM `Product` p\n"
                + "WHERE p.`create_date` BETWEEN ? AND ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, (java.sql.Date) fdate);
            ps.setDate(2, (java.sql.Date) sdate);
            ResultSet rs = ps.executeQuery();

           while (rs.next()) {
                list.add(new Product(
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getDouble("product_price"),
                rs.getInt("setting_id"),
                rs.getString("img"),
                rs.getString("description"),
                rs.getInt("product_status"),
                rs.getDate("create_date"),
                rs.getInt("size")));
            }
        } catch (SQLException e) {

        }
        return list;
    }

}
