/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Product;
import Model.Setting;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO extends DBContext {

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM swp391.product";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productList.add(new Product(
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

        return productList;
    }

    public ArrayList<Product> getAllProduct(String cid, String search, int index, String sort) {
        String sortby;
        switch (sort) {
            case "1":
                sortby = "order by p.create_date desc";
                break;
            case "2":
                sortby = "order by p.product_price asc";
                break;
            case "3":
                sortby = "order by p.product_price desc";
                break;
            default:
                sortby = "order by p.product_name desc";
                break;

        }
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT p.* FROM swp391.product p \n"
                + "JOIN swp391.setting s ON p.setting_id = s.setting_id\n"
                + "WHERE p.product_name LIKE ? \n"
                + "AND s.setting_id = ?\n"
                + "AND p.product_status = 1\n"
                + sortby + "\n"
                + "LIMIT ?, 12;";//
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(2, cid);
            ps.setString(1, "%" + search + "%");
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

    public static void main(String[] args) {
        ProductDAO ProductDAO = new ProductDAO();
        ArrayList<Product> list = ProductDAO.getAllProduct("5", "", 1, "2");
        ArrayList<Setting> lists = ProductDAO.getCategory();
        ArrayList<Product> list1 = ProductDAO.getAllProducts();
        ArrayList<Product> list12 = ProductDAO.getTopSelling();
        Product p1 = ProductDAO.getProductById(8);
        int a = ProductDAO.getNumberProduct("4", "a");
        System.out.println(a);
        for (Product p : list12) {
            System.out.println(p);
        }

    }

    public ArrayList<Setting> getCategory() {
        ArrayList<Setting> list = new ArrayList<>();
        String sql = "  SELECT * FROM swp391.setting;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Setting(rs.getInt("setting_id"), rs.getString("setting_name"), rs.getString("description"), rs.getString("type"), rs.getInt("setting_sort")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getNumberProduct(String cid, String search) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = " select count(*) from swp391.product p \n"
                + "JOIN swp391.setting s ON p.setting_id = s.setting_id\n"
                + "  where p.setting_id like ?  and p.product_name like ? and p.product_status = 1 ";
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

    public Product getProductById(int pid) {
        String sql = "SELECT p.* FROM swp391.product p \n"
                + "JOIN swp391.setting s ON p.setting_id = s.setting_id\n"
                + "WHERE p.product_id=?\n"
                + "AND p.product_status = 1";
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

    public ArrayList<Product> getTopSelling() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT p.*\n"
                + "             FROM swp391.product p  \n"
                + "             JOIN (SELECT SUM(quantity) AS numberSell, product_id FROM swp391.orderdetail GROUP BY product_id) AS b ON b.product_id = p.product_id  \n"
                + "               \n"
                + "             WHERE p.product_status = 1  \n"
                + "             ORDER BY b.numberSell DESC  \n"
                + "             LIMIT 8;";
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
        } catch (SQLException e) {
        }
        return list;
    }
//    public ArrayList<Product> getProduct(String cid, String search, int index, String sort) {
//        String sortby = "";
//        switch (sort) {
//            case "1":
//                sortby = "order by p.create_date desc";
//                break;
//            case "2":
//                sortby = "order by p.product_price asc";
//                break;
//            case "3":
//                sortby = "order by p.product_price desc";
//                break;
//            default:
//                sortby = "order by p.product_name desc";
//                break;
//
//        }
//        ArrayList<Product> list = new ArrayList<>();
//        String sql = "  select * from [Product] p, Category c, ProductStatus ps "
//                + "where p.category_id = c.category_id and p.PdStatus_id = ps.PdStatus_id and p.PdStatus_id = 1\n"
//                + "                and p.category_id like ?  and p.product_name like ?\n"
//                + sortby
//                + "                OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
//
//        return list;
//    }

}
