/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Category;
import Model.Product;
import Model.ProductSize;
import Model.ProductStatus;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProductDAO extends DBContext {

//    public static void main(String[] args) {
//
//        ProductDAO ProductDAO = new ProductDAO();
//        Date fdate = Date.valueOf("2023-05-18");
//        Date sdate = Date.valueOf("2023-05-30");
//        ArrayList<Product> list = ProductDAO.getProductByDate(fdate, sdate);
//        for (Product p : list) {
//            System.out.println(p);
//        }
//
//    }
    public ArrayList<Product> getProduct(String cid, String search, int index, String sort) {
        String sortby ="";
        switch (sort) {
            case "1":
                sortby = " p.`create_date` desc";
                break;
            case "2":
                sortby = " p.`product_price` asc";
                break;
            case "3":
                sortby = " p.`product_price` desc";
                break;
            default:
                sortby = " p.`product_name` desc";
                break;

        }
        ArrayList<Product> list = new ArrayList<>();
        String sql = " SELECT *\n"
                + "FROM `Product` p\n"
                + "JOIN `Category` c ON p.`category_id` = c.`category_id`\n"
                + "JOIN `ProductStatus` ps ON p.`PdStatus_id` = ps.`PdStatus_id`\n"
                + "WHERE p.`PdStatus_id` = 1\n"
                + "  AND p.`category_id` LIKE ?\n"
                + "  AND p.`product_name` LIKE ?\n"
                + "ORDER BY "+sortby+ " LIMIT ?, 8;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + cid + "%");
            ps.setString(2, "%" + search + "%");
            ps.setInt(3, (index - 1) * 6);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(5),
                        new Category(rs.getInt(9), rs.getString(10)),
                        rs.getString(6),
                        new ProductStatus(rs.getInt(11), rs.getString(12)),
                        rs.getDate(8)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public ArrayList<Product> getTopSelling() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT p.*, c.*, ps.*\n"
                + "FROM `Product` p\n"
                + "JOIN `ProductStatus` ps ON p.`PdStatus_id` = ps.`PdStatus_id`\n"
                + "JOIN (\n"
                + "  SELECT SUM(quantity) AS numberSell, product_id\n"
                + "  FROM `OrderDetail`\n"
                + "  GROUP BY product_id\n"
                + ") AS b ON b.product_id = p.product_id\n"
                + "JOIN `Category` c ON p.category_id = c.category_id\n"
                + "WHERE p.PdStatus_id = 1\n"
                + "ORDER BY b.numberSell DESC\n"
                + "LIMIT 8;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(5),
                        new Category(rs.getInt(9), rs.getString(10)),
                        rs.getString(6),
                        new ProductStatus(rs.getInt(11), rs.getString(12)),
                        rs.getDate(8)));
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
                + "JOIN `Category` c ON p.`category_id` = c.`category_id`\n"
                + "JOIN `ProductStatus` ps ON p.`PdStatus_id` = ps.`PdStatus_id`\n"
                + "WHERE p.`PdStatus_id` = 1\n"
                + "  AND p.`category_id` LIKE ?\n"
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
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(5),
                        new Category(rs.getInt(9), rs.getString(10)),
                        rs.getString(6),
                        new ProductStatus(rs.getInt(11), rs.getString(12)),
                        rs.getDate(8)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public Product getProductById(int pid) {
        String sql = "SELECT *\n"
                + "FROM `Product` p\n"
                + "JOIN `Category` c ON p.`category_id` = c.`category_id`\n"
                + "JOIN `ProductStatus` ps ON p.`PdStatus_id` = ps.`PdStatus_id`\n"
                + "WHERE p.`product_id` = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(5),
                        new Category(rs.getInt(9), rs.getString(10)),
                        rs.getString(6),
                        new ProductStatus(rs.getInt(11), rs.getString(12)),
                        rs.getDate(8));
                return p;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<Category> getCategory() {
        ArrayList<Category> list = new ArrayList<>();
        String sql = "  select* from `Category`";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt("category_id"), rs.getString("category_name")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getNumberProduct(String cid, String search) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "  select count(*) from `Product` p, `Category` c where p.`category_id` = c.`category_id` \n"
                + "  and p.`category_id` like ?  and p.`product_name` like ?";
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

    public ArrayList<ProductSize> getProductSize() {
        ArrayList<ProductSize> list = new ArrayList<>();
        String sql = "select * from `ProductSize`";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductSize(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void AddProduct(String name, double price, int cateId, String descri, String img) {
        String sql = "INSERT INTO `Product`\n"
                + "  (`product_name`, `product_price`, `PdStatus_id`, `category_id`, `img`, `description`, `create_date`)\n"
                + "VALUES\n"
                + "  (?, ?, 1, ?, ?, ?, NOW());";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, cateId);
            ps.setString(4, img);
            ps.setString(5, descri);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void UpdateProduct(int id, String name, double price, int cateId, String descri, String img) {
        String sql = "UPDATE `Product`\n"
                + "SET `product_name` = ?, `product_price` = ?, `category_id` = ?,\n"
                + "    `img` = ?, `description` = ?, `create_date` = NOW()\n"
                + "WHERE `product_id` = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, cateId);
            ps.setString(4, img);
            ps.setString(5, descri);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void UpdateProductStatus(int psId, int pId) {
        String sql = "update `Product` set `PdStatus_id` = ? where `product_id` = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, psId);
            ps.setInt(2, pId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public ArrayList<Product> getAllProductByStatus() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT p.product_id, p.product_name, p.product_price,\n"
                + "       c.category_id, c.category_name, ps.PdStatus_id,\n"
                + "       ps.PdStatus_name, p.create_date, p.description, p.img\n"
                + "FROM `Product` p\n"
                + "INNER JOIN `Category` c ON p.`category_id` = c.`category_id`\n"
                + "INNER JOIN `ProductStatus` ps ON p.`PdStatus_id` = ps.`PdStatus_id`;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(10),
                        new Category(rs.getInt(4), rs.getString(5)),
                        rs.getString(9),
                        new ProductStatus(rs.getInt(6), rs.getString(7)),
                        rs.getDate(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Product> getAllProducts(String cid, String search) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = " SELECT *\n"
                + "FROM `Product` p\n"
                + "JOIN `Category` c ON p.`category_id` = c.`category_id`\n"
                + "JOIN `ProductStatus` ps ON p.`PdStatus_id` = ps.`PdStatus_id`\n"
                + "WHERE p.`category_id` LIKE ?\n"
                + "  AND p.`product_name` LIKE ?\n"
                + "ORDER BY p.`product_id` ASC;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + cid + "%");
            ps.setString(2, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(5),
                        new Category(rs.getInt(9), rs.getString(10)),
                        rs.getString(6),
                        new ProductStatus(rs.getInt(11), rs.getString(12)),
                        rs.getDate(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

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

    public ArrayList<Product> searchProduct(String search) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = " SELECT *\n"
                + "FROM `Product` p\n"
                + "JOIN `Category` c ON p.`category_id` = c.`category_id`\n"
                + "JOIN `ProductStatus` ps ON p.`PdStatus_id` = ps.`PdStatus_id`\n"
                + "WHERE p.`product_name` LIKE ?\n"
                + "ORDER BY p.`product_id` ASC;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(5),
                        new Category(rs.getInt(9), rs.getString(10)),
                        rs.getString(6),
                        new ProductStatus(rs.getInt(11), rs.getString(12)),
                        rs.getDate(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Product> getProductByDate(Date fdate, Date sdate) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM `Product` p\n"
                + "JOIN `Category` c ON p.`category_id` = c.`category_id`\n"
                + "JOIN `ProductStatus` ps ON p.`PdStatus_id` = ps.`PdStatus_id`\n"
                + "WHERE p.`create_date` BETWEEN ? AND ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, (java.sql.Date) fdate);
            ps.setDate(2, (java.sql.Date) sdate);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(5),
                        new Category(rs.getInt(9), rs.getString(10)),
                        rs.getString(6),
                        new ProductStatus(rs.getInt(11), rs.getString(12)),
                        rs.getDate(8)));

            }
        } catch (SQLException e) {

        }
        return list;
    }

}
