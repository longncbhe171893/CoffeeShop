/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Category;
import Model.OrderDetail;
import Model.Order;
import Model.Product;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO extends DBContext {
    
    public void insertOrder(User user, String name, int discount, String note, String address, String phone, List<Product> map, int quantity) {
        String sql;
        if (user != null) {
            sql = "INSERT INTO `Order`\n"
                    + "    (`user_id`, `order_name`, `order_Status`, `order_discount`, `order_date`, `notes`, `order_address`, `order_phone`)\n"
                    + "VALUES\n"
                    + "    (?, ?, 1, ?, NOW(), ?, ?, ?);";
        } else {
            sql = "INSERT INTO `Order`\n"
                    + "    (`order_name`, `order_Status`, `order_discount`, `order_date`, `notes`, `order_address`, `order_phone`)\n"
                    + "VALUES\n"
                    + "    ( ?, ?, ?, NOW(), ?, ?,?);";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            if (user != null) {
                ps.setInt(1, user.getId());
                ps.setString(2, name);
                ps.setInt(3, discount);
//                ps.setDate(4, new java.sql.Date(date.getTime()));
                ps.setString(4, note);
                ps.setString(5, address);
                ps.setString(6, phone);
            } else {
                ps.setString(1, name);
                ps.setInt(2, discount);
//                ps.setDate(3, new java.sql.Date(date.getTime()));
                ps.setString(3, note);
                ps.setString(4, address);
                ps.setString(5, phone);
            }
            ps.executeUpdate();
            
            String xSQL = "SELECT * FROM `Orders` ORDER BY order_id DESC LIMIT 1;";
            ps = connection.prepareStatement(xSQL);
            ResultSet rs = ps.executeQuery();
            int id = -99;
            if (rs.next()) {
                id = rs.getInt("order_id");
            }
            String qSQL = "INSERT INTO `orderdetail`\n"
                    + "    (`order_id`, `product_id`, `order_price`, `quantity`, `productSize_id`)\n"
                    + "VALUES\n"
                    + "    (?, ?, ?, ?, ?);";
            String zSQL = "INSERT INTO `orderdetail`\n"
                    + "    (`order_id`, `product_id`, `order_price`, `quantity`)\n"
                    + "VALUES\n"
                    + "    (?, ?, ?, ?);";
            for (Product i : map) {
                ps = connection.prepareStatement(qSQL);
                ps.setInt(1, id);
                ps.setInt(2, i.getId());
                switch (i.getSize()) {
                    case 1:
                        ps.setDouble(3, i.getPrice());
                        break;
                    case 2:
                        ps.setDouble(3, i.getPrice() + 10);
                        break;
                    default:
                        ps.setDouble(3, i.getPrice() + 15);
                        break;
                }
                ps.setInt(4, i.getQuantity());
                ps.setInt(5, i.getSize());
                ps.executeUpdate();
            }
//                } else {
//                    ps = connection.prepareStatement(zSQL);
//                    ps.setInt(1, id);
//                    ps.setInt(2, i.getProduct().getId());
//                    ps.setDouble(3, i.getProduct().getPrice());
//                    ps.setInt(4, i.getQuantity());
//                    ps.executeUpdate();
//                }

        } catch (SQLException e) {
        }
    }
    
    public void updateUser(int point, int id) {
        try {
            String sql = "UPDATE Users\n"
                    + "SET user_point = ?\n"
                    + "WHERE user_id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, point);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            
        }
    }
    public static void main(String[] args) {
        OrderDAO od = new OrderDAO();
        ArrayList<OrderDetail> mc= od.getAllOrderDetail();
        for (OrderDetail category : mc) {
            System.out.println(category);
        }
    }

    public ArrayList<Order> getOrderProcess() {
        ArrayList<Order> list = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "FROM `Order` o WHERE order_Status = 1\n"
                    + "ORDER BY o.order_date DESC;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                list.add(new Order(rs.getInt(1), getUserById(rs.getInt(2)), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getTimestamp(6),
                        rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException e) {
            
        }
        return list;
    }
    
    public User getUserById(int id) {
        User u = new User();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "select* from `users` where `user_id` = ?;";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), "", rs.getInt(9), rs.getInt(10), rs.getInt(11));
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return u;
    }
    
    public ArrayList<Order> getOrder() {
        ArrayList<Order> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `Orders` o ORDER BY o.order_date DESC;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                list.add(new Order(rs.getInt(1), getUserById(rs.getInt(2)), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getTimestamp(6),
                        rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException e) {
            
        }
        return list;
    }
    
    public ArrayList<Order> getOrderSt() {
        ArrayList<Order> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM orders where order_status = 2 or order_status = 3";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1), getUserById(rs.getInt(2)), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getTimestamp(6),
                        rs.getString(7), rs.getString(8), rs.getString(9)));
            }
            
        } catch (SQLException e) {
        }
        return list;
    }
    
    public void UpdateStatusOrder(int osId, int oid) {
        try {
            String sql = "UPDATE `Orders`\n"
                    + "SET `order_status` = ?\n"
                    + "WHERE `order_id` = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, osId);
            ps.setInt(2, oid);
            ps.executeUpdate();
        } catch (SQLException e) {
            
        }
    }
    
    public List<Order> getOrderByUser(int id) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `Orders` WHERE `user_id` = ? ORDER BY `order_date` DESC;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                list.add(new Order(rs.getInt(1), getUserById(rs.getInt(2)), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getTimestamp(6),
                        rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException e) {
            
        }
        return list;
    }
    
    public Order getOrderById(int id) {
        try {
            String sql = "SELECT * FROM `Orders` WHERE `order_id` = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return (new Order(rs.getInt(1), getUserById(rs.getInt(2)), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getTimestamp(6),
                        rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException e) {
            
        }
        return null;
    }
    
    public ArrayList<Order> getOrderByDate(Date fdate, Date sdate) {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "select * from `Order`  `order_status` = 2 and  o.`order_date` BETWEEN ? AND ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, (java.sql.Date) fdate);
            ps.setDate(2, (java.sql.Date) sdate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                list.add(new Order(rs.getInt(1), getUserById(rs.getInt(2)), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getTimestamp(6),
                        rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException e) {
            
        }
        return list;
    }
    
    public ArrayList<OrderDetail> getOrderDetail(int odid) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM orderdetail where order_id = ?;";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, odid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = getOrderById(odid);
                Product product = getProductById(rs.getInt(3));
                double order_price = rs.getDouble(4);
                int quantity = rs.getInt(5);
                int productSize = product.getSize();
//                int orderId = rs.getInt("order_id");
//                String orderName = rs.getString("order_name");
//                int order_status = rs.getInt("order_status");
//                Date orderDate = rs.getDate("order_date");
//                String productName = rs.getString("product_name");
//                String productSizeName = rs.getString("productSize_name");
//                double orderPrice = rs.getDouble("order_price");
//                int quantity = rs.getInt("quantity");
//                int discount = rs.getInt("order_discount");
//                String notes = rs.getString("notes");
//                String orderAddress = rs.getString("order_address");
//                String orderPhone = rs.getString("order_phone");
//                double amount = rs.getDouble("amount");
//                int userId = rs.getInt("user_id");
                OrderDetail orderDetail = new OrderDetail(odid, order, product, order_price, quantity,order_price*quantity);
                list.add(orderDetail);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return list;
    }
    
    public ArrayList<OrderDetail> getAllOrderDetail() {
        ArrayList<OrderDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM orderdetail order by detail_id desc;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int odid = rs.getInt(1);
                Order order = getOrderById(odid);
                Product product = getProductById(rs.getInt(3));
                double order_price = rs.getDouble(4);
                int quantity = rs.getInt(5);
                int productSize = product.getSize();
                 OrderDetail orderDetail = new OrderDetail(odid, order, product, order_price, quantity,order_price*quantity);
                list.add(orderDetail);
            }
        } catch (SQLException e) {
            
        }
        return list;
    }
    
    public int countOrder() {
        int count = 0;
        try {
            String sql = "select count(*) from `Orders` ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
                return count;
            }
        } catch (Exception e) {
            
        }
        return 0;
    }
    
    public int countUser() {
        int count;
        try {
            String sql = " select count(*) from `Users` where `setting_id` = 3";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
            return count;
            
        } catch (SQLException e) {
            
        }
        return 0;
    }
    
    public int sumAmount() {
        int count;
        try {
            String sql = "SELECT SUM(total_amount) AS total_sum\n"
                    + "FROM (\n"
                    + "    SELECT SUM(od.quantity * od.order_price) AS total_amount\n"
                    + "    FROM `OrderDetail` od \n"
                    + "    INNER JOIN `Product` p ON od.`product_id` = p.`product_id`\n"
                    + "    INNER JOIN `ProductSize` ps ON od.`productSize_id` = ps.`productSize_id`\n"
                    + "    INNER JOIN `Order` o ON o.`order_id` = od.`order_id`\n"
                    + "    WHERE o.`orderStatus_id` = 2\n"
                    + "    GROUP BY od.`detail_id`, od.`order_id`, p.`product_name`, od.`order_price`, od.`quantity`, ps.`productSize_name`, p.`img`\n"
                    + ") AS subquery;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
                return count;
            }
        } catch (SQLException e) {
            
        }
        return 0;
    }
    
    private Product getProductById(int pro_id) {
        try {
            String sql = "SELECT * FROM `product` WHERE `product_id` = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pro_id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8), rs.getInt(9));
            
        } catch (SQLException e) {
            
        }
        return null;
    }
    
}
