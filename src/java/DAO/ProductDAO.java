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

    public ArrayList<Product> getSlider() {
        ArrayList<Product> list = new ArrayList();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT p.product_id, p.product_name, p.img, p.product_status, p.create_date  FROM `product` p WHERE p.product_status IN (1, 3) order by p.`product_status` desc;";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString("img"), rs.getInt("product_status"), rs.getDate("create_date"));
                list.add(product);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return list;
    }

    public void updateSlider(String title, String img, String id, String status) {
        String sql = "UPDATE `Product`\n"
                + "SET `product_name` = ?, `img` = ?,`product_status` = ?,`create_date` = NOW()\n"
                + "WHERE `product_id` = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, img);
            ps.setString(3, id);
            ps.setString(4, status);

            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void UpdateStatusSlider(int status, int id) {
        String sql = " update `Product` set `product_status`=? where `product_id` =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addSlider(String title, String img) {
        String sql = "INSERT INTO `Product`\n"
                + "  (`product_name`,`setting_id`, `img`,`product_status`, `create_date`)\n"
                + "VALUES\n"
                + "  (?,'4', ?,'3', NOW());";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, img);

            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public ArrayList<Product> getSliderWithPagination(int offset, int recordsPerPage) {
        ArrayList<Product> list = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT p.product_id, p.product_name, p.img, p.product_status, p.create_date FROM `product` p WHERE p.product_status IN (1, 3) ORDER BY p.`product_status` DESC LIMIT ?, ?;";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, offset);
            ps.setInt(2, recordsPerPage);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString("img"), rs.getInt("product_status"), rs.getDate("create_date"));
                list.add(product);
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
            String query = "SELECT COUNT(*) AS total FROM `product` WHERE `product_status` IN (1, 3)";
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
}
