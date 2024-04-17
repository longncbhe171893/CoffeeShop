package DAO;

import Model.Blog;
import Model.Category;
import Model.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogDao extends DBContext {

    public Blog getBlogByBlogId(int blogId) {
        Blog blog;
        String sql = "SELECT b.blog_id, b.blog_title, b.blog_image, b.user_id, b.`post_date`, b.Content  FROM `blog` b  \n"
                + "WHERE b.`blog_id` = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, blogId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                blog = new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), getUserById(rs.getInt(4)), rs.getDate(5), rs.getString(6));
                return blog;
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public ArrayList<Model.Category> getcategoryBlogBySettingId() {
        ArrayList<Model.Category> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM category where `setting_id` = 5;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2), rs.getInt(3)));                
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
            e.printStackTrace();
        }
        return u;
    }

    public ArrayList<Blog> getBlogs() {
        ArrayList<Blog> list = new ArrayList();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT b.blog_id, b.blog_title, b.blog_image, b.user_id, b.post_date, b.content FROM `blog` b order by b.`post_date` desc;";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Blog blog = new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), getUserById(rs.getInt(4)), rs.getDate(5), rs.getString(6));
//                Blog blog = new Blog(blog_id, blog_title, blog_image, user_id, post_date, content);
                list.add(blog);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return list;
    }
    // phan trang trả về danh sách blog trong page

    public List<Blog> getListByPage(List<Blog> list, int start, int end) {
        ArrayList<Blog> listByPage = new ArrayList<>();

        for (int i = start; i < end; i++) {
            listByPage.add(list.get(i));
        }
        return listByPage;
    }

    public ArrayList<Blog> recentBlog() {
        ArrayList<Blog> list = new ArrayList<>();
        String sql = "SELECT b.* FROM `Blog` b ORDER BY `post_date` DESC\n"
                + "LIMIT 3;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Blog(
                        rs.getInt(1), rs.getString(2), rs.getString(3),
                        getUserById(rs.getInt(4)),
                        rs.getDate(5),
                        rs.getString(6)));

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (list);
    }

    public void addBlog(String title, String img, int userId, String content) {
        String sql = "INSERT INTO `Blog`\n"
                + "  (`blog_title`, `blog_image`, `user_id`, `post_date`, `content`)\n"
                + "VALUES\n"
                + "  (?, ?, ?, NOW(), ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, img);
            ps.setInt(3, userId);
            ps.setString(4, content);
            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateBlog(String title, String img, String content, int id) {
        String sql = "UPDATE `Blog`\n"
                + "SET `blog_title` = ?, `blog_image` = ?, `content` = ?, `post_date` = NOW()\n"
                + "WHERE `blog_id` = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, img);
            ps.setString(3, content);
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void DeleteBlog(int bid) {
        String sql = "  DELETE FROM `Blog` WHERE `blog_id` =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, bid);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public ArrayList<Blog> searchBlog(String search) {

        ArrayList<Blog> list = new ArrayList<>();
        String sql = "SELECT b.blog_id, b.blog_title, b.blog_image, b.user_id, b.`post_date`, b.content\n"
                + "FROM `Blog` b\n WHERE `blog_title` LIKE ?\n"
                + "ORDER BY `blog_title` ASC;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), getUserById(rs.getInt(4)), rs.getDate(5), rs.getString(6))
                );
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Blog> getBlogByDate(Date fdate, Date sdate) {
        ArrayList<Blog> list = new ArrayList<>();
        String sql = "SELECT b.blog_id, b.blog_title, b.blog_image, b.user_id, b.`post_date`, b.content\n"
                + "FROM `Blog` b WHERE b.`post_date` BETWEEN ? AND ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, (java.sql.Date) fdate);
            ps.setDate(2, (java.sql.Date) sdate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                list.add(new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), getUserById(rs.getInt(4)), rs.getDate(5), rs.getString(6)));
            }
        } catch (SQLException e) {

        }
        return list;
    }
//
//    public static void main(String[] args) {
//        BlogDao bld = new BlogDao();
//        bld.DeleteBlog(7);
//        
//
//    }
}
