/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Product;
import Model.ProductSize;
import Model.Setting;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO extends DBContext {
    public ProductSize getProductSizeByID(int id) {
        try {
            String sql = "SELECT * FROM swp391.productsize WHERE productSize_id =?";
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
    public ArrayList<ProductSize> getProductSize() {
        ArrayList<ProductSize> list = new ArrayList<>();
        String sql = "select * from ProductSize";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductSize(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public ArrayList<Product> getAllSlideProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM swp391.product where product_status=3;";

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
        ArrayList<ProductSize> list12 = ProductDAO.getProductSize();
        Product p1 = ProductDAO.getProductById(8);
        int a = ProductDAO.getNumberProduct("4", "a");
        System.out.println(p1);
        for (ProductSize p : list12) {
            System.out.println(p);
        }
        System.out.println(ProductDAO.getProductSizeByID(1));
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


//    public ArrayList<Product> getSlider() {
//        ArrayList<Product> list = new ArrayList();
//        PreparedStatement ps;
//        ResultSet rs;
//        String sql = "SELECT p.product_id, p.product_name, p.img, p.product_status, p.create_date  FROM `product` p WHERE p.product_status IN (1, 3) order by p.`product_status` desc;";
//        try {
//            ps = connection.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//
//                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString("img"), rs.getInt("product_status"), rs.getDate("create_date"));
//                list.add(product);
//            }
//            rs.close();
//            ps.close();
//        } catch (SQLException e) {
//        }
//        return list;
//    }

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
//
//    public ArrayList<Product> getSliderWithPagination(int offset, int recordsPerPage) {
//        ArrayList<Product> list = new ArrayList<>();
//        PreparedStatement ps;
//        ResultSet rs;
//        String sql = "SELECT p.product_id, p.product_name, p.img, p.product_status, p.create_date FROM `product` p WHERE p.product_status IN (1, 3) ORDER BY p.`product_status` DESC LIMIT ?, ?;";
//        try {
//            ps = connection.prepareStatement(sql);
//            ps.setInt(1, offset);
//            ps.setInt(2, recordsPerPage);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString("img"), rs.getInt("product_status"), rs.getDate("create_date"));
//                list.add(product);
//            }
//            rs.close();
//            ps.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

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
    /////
    
    
    
    
    
    ////
     public ArrayList<Product> pagingProduct(int index, int numOrOnPage, int settingId, int productStatus, String searchProductName) {
    ArrayList<Product> list = new ArrayList<>();
    try {
        String sql = "SELECT * FROM `Product`";
        // Thêm điều kiện WHERE nếu có bất kỳ tham số nào khác null hoặc searchProductName không rỗng
        if (settingId != 0 || productStatus != 0 || !searchProductName.isEmpty()) {
            sql += " WHERE ";
            boolean isFirstCondition = true;
            // Thêm điều kiện cho setting_id
            if (settingId != 0) {
                if (!isFirstCondition) {
                    sql += " AND ";
                }
                sql += " `setting_id` = ? ";
                isFirstCondition = false;
            }
            // Thêm điều kiện cho product_status
            if (productStatus != 0) {
                if (!isFirstCondition) {
                    sql += " AND ";
                }
                sql += " `product_status` = ? ";
                isFirstCondition = false;
            }
            // Thêm điều kiện cho tìm kiếm theo tên sản phẩm
            if (!searchProductName.isEmpty()) {
                if (!isFirstCondition) {
                    sql += " AND ";
                }
                sql += " `product_name` LIKE ? ";
            }
        }
        sql += " ORDER BY create_date ASC LIMIT ?, ?";
        
        PreparedStatement ps = connection.prepareStatement(sql);
        int parameterIndex = 1;
        // Đặt các giá trị tham số nếu chúng không 0 hoặc không rỗng
        if (settingId != 0) {
            ps.setInt(parameterIndex++, settingId);
        }
        if (productStatus != 0) {
            ps.setInt(parameterIndex++, productStatus);
        }
        // Đặt giá trị tham số cho tìm kiếm theo tên sản phẩm nếu có
        if (!searchProductName.isEmpty()) {
            ps.setString(parameterIndex++, "%" + searchProductName + "%");
        }
        // Thiết lập giá trị index và số trang
        index = (index - 1) * numOrOnPage;
        ps.setInt(parameterIndex++, index);
        ps.setInt(parameterIndex, numOrOnPage);
        
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
        // Xử lý ngoại lệ nếu cần
    }
    return list;
}
    public int countProduct(int settingId, int productStatus, String searchProductName) {
    int count = 0;
    try {
        String sql = "SELECT COUNT(*) FROM `Product`";
        // Thêm điều kiện WHERE nếu có bất kỳ tham số nào khác 0 hoặc searchProductName không rỗng
        if (settingId != 0 || productStatus != 0 || !searchProductName.isEmpty()) {
            sql += " WHERE ";
            boolean isFirstCondition = true;
            // Thêm điều kiện cho setting_id
            if (settingId != 0) {
                if (!isFirstCondition) {
                    sql += " AND ";
                }
                sql += " `setting_id` = ? ";
                isFirstCondition = false;
            }
            // Thêm điều kiện cho product_status
            if (productStatus != 0) {
                if (!isFirstCondition) {
                    sql += " AND ";
                }
                sql += " `product_status` = ? ";
                isFirstCondition = false;
            }
            // Thêm điều kiện cho tìm kiếm theo tên sản phẩm
            if (!searchProductName.isEmpty()) {
                if (!isFirstCondition) {
                    sql += " AND ";
                }
                sql += " `product_name` LIKE ? ";
            }
        }
        
        PreparedStatement ps = connection.prepareStatement(sql);
        int parameterIndex = 1;
        // Đặt các giá trị tham số nếu chúng không 0 hoặc không rỗng
        if (settingId != 0) {
            ps.setInt(parameterIndex++, settingId);
        }
        if (productStatus != 0) {
            ps.setInt(parameterIndex++, productStatus);
        }
        // Đặt giá trị tham số cho tìm kiếm theo tên sản phẩm nếu có
        if (!searchProductName.isEmpty()) {
            ps.setString(parameterIndex++, "%" + searchProductName + "%");
        }
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException e) {
        // Xử lý ngoại lệ nếu cần
    }
    return count;
}
    public Product GetProductById(int pid) {
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
   public ArrayList<Setting> GetCategory() {
        ArrayList<Setting> list = new ArrayList<>();
        String sql = "  select `setting_id`,`setting_name` from `Setting` where `type`='Category'";
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


    public Product getAllProductById(int pid) {
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

    public ArrayList<Setting> getAllCategory() {
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
   public void AddProduct(String name, double price, int cateId,  String img,String descri) {
        String sql = "INSERT INTO `Product`\n"
                + "  (`product_name`, `product_price`, `product_status`, `setting_id`, `img`, `description`, `create_date`,`size`)\n"
                + "VALUES\n"
                + "  (?, ?, 1, ?, ?, ?, NOW(),1);";
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

     public void UpdateProduct (String name, double price, int cateId,  String img,String descri,int id) {
        String sql = "UPDATE `Product`\n"
                + "SET `product_name` = ?, `product_price` = ?, `setting_id` = ?,\n"
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
        String sql = "update `Product` set `product_status` = ? where `product_id` = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, psId);
            ps.setInt(2, pId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    /*public ArrayList<Product> searchProduct(String search) {
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
*/
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
