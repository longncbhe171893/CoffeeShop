package DAO;

import Model.Blog;
import Model.Category;
import Model.User;
import Model.Setting;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogDao extends DBContext {
    public ArrayList<Blog> getBlogBySearchTilte(String search) {
        ArrayList<Blog> list = new ArrayList();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM coffeeshop.blog WHERE blog_title LIKE ?;";
        try {
            ps = connection.prepareStatement(sql);
                        ps.setString(1, "%" + search + "%");

            rs = ps.executeQuery();

            while (rs.next()) {

                Blog blog = new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), getUserById(rs.getInt(4)), rs.getDate(5), rs.getString(6), getSettingById(rs.getInt(7)), rs.getInt(8), rs.getString(9));
//                Blog blog = new Blog(blog_id, blog_title, blog_image, user_id, post_date, content);
                list.add(blog);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return list;
    }
    
    public ArrayList<Blog> getBlogByCategory(int cat) {
        ArrayList<Blog> list = new ArrayList();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM coffeeshop.blog where setting_id=?;";
        try {
            ps = connection.prepareStatement(sql);
                        ps.setInt(1, cat);

            rs = ps.executeQuery();

            while (rs.next()) {

                Blog blog = new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), getUserById(rs.getInt(4)), rs.getDate(5), rs.getString(6), getSettingById(rs.getInt(7)), rs.getInt(8), rs.getString(9));
//                Blog blog = new Blog(blog_id, blog_title, blog_image, user_id, post_date, content);
                list.add(blog);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return list;
    }
    public Blog getBlogByBlogId(int blogId) {
        Blog blog;
        String sql = "SELECT * FROM `blog` "
                + "WHERE `blog_id` = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, blogId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                blog = new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), getUserById(rs.getInt(4)), rs.getDate(5), rs.getString(6), getSettingById(rs.getInt(7)), rs.getInt(8), rs.getString(9));
                return blog;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Model.Category> getcategoryBlogByType() {
        ArrayList<Model.Category> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM swp391.setting where type like 'Blog';";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
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
        String sql = "SELECT * FROM `blog` b order by b.`post_date` desc;";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Blog blog = new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), getUserById(rs.getInt(4)), rs.getDate(5), rs.getString(6), getSettingById(rs.getInt(7)), rs.getInt(8), rs.getString(9));
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
        String sql = "SELECT * FROM `Blog`  ORDER BY `post_date` DESC\n"
                + "LIMIT 3;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Blog(rs.getInt(1), rs.getString(2), rs.getString(3),
                        getUserById(rs.getInt(4)), rs.getDate(5), rs.getString(6), getSettingById(rs.getInt(7)),
                        rs.getInt(8), rs.getString(9)));

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (list);
    }

    public static void main(String[] args) {
        BlogDao bld = new BlogDao();
        ArrayList<Blog> bl = bld.pagingBlogs(1, 4, 2, 33);
        for (Blog blog : bl) {
            System.out.println(blog);
        }

    }

    public void addBlog(String title, String img, int userId, String content, int setting_id, String short_descreption) {

        String sql = "INSERT INTO `Blog`\n"
                + "  (`blog_title`, `blog_image`, `user_id`, `post_date`, `content`, `setting_id`, `blog_status`,`short_descreption`)\n"
                + "VALUES\n"
                + "  (?, ?, ?, NOW(), ?,?,2,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, img);
            ps.setInt(3, userId);
            ps.setString(4, content);
            ps.setInt(5, setting_id);
            ps.setString(6, short_descreption);
            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateBlog(int blogId, String title, String img, int userId, String content, int setting_id, String short_descreption) {
        String sql = "UPDATE `Blog`\n"
                + "SET `blog_title` = ?, `blog_image` = ?,`user_id` =?, `content` = ?, `post_date` = NOW(),`setting_id`=?,`short_descreption`=?\n"
                + "WHERE `blog_id` = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, img);
            ps.setInt(3, userId);

            ps.setString(4, content);
            ps.setInt(5, setting_id);
            ps.setString(6, short_descreption);
            ps.setInt(7, blogId);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public ArrayList<Blog> searchBlog(String search, int role, int sellerId) {

        ArrayList<Blog> list = new ArrayList<>();
        if (role == 1) {
            String sql = "SELECT * from blog WHERE `blog_title` LIKE ?\n"
                    + "ORDER BY `blog_title` ASC;";
            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), getUserById(rs.getInt(4)), rs.getDate(5), rs.getString(6), getSettingById(rs.getInt(7)), rs.getInt(8), rs.getString(9))
                    );
                }
            } catch (SQLException e) {
            }
        } else if (role == 2) {
            String sql = "SELECT * from blog WHERE `user_id` =? and `blog_title` LIKE ?\n"
                    + "ORDER BY `blog_title` ASC;";
            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(2, "%" + search + "%");
                ps.setInt(1, sellerId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), getUserById(rs.getInt(4)), rs.getDate(5), rs.getString(6), getSettingById(rs.getInt(7)), rs.getInt(8), rs.getString(9))
                    );
                }
            } catch (SQLException e) {
            }
        }
        return list;
    }

    public ArrayList<Blog> getBlogByDate(Date fdate, Date sdate) {
        ArrayList<Blog> list = new ArrayList<>();
        String sql = "SELECT * FROM `Blog` b WHERE b.`post_date` BETWEEN ? AND ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, (java.sql.Date) fdate);
            ps.setDate(2, (java.sql.Date) sdate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                list.add(new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), getUserById(rs.getInt(4)), rs.getDate(5), rs.getString(6), getSettingById(rs.getInt(7)), rs.getInt(8), rs.getString(9)));
            }
        } catch (SQLException e) {

        }
        return list;
    }
//

    public void changeStatusBlog(int blogId) {
        String sql = "Update Blog set `blog_status` = ? where blog_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            if (getBlogByBlogId(blogId).getBlog_status() == 1) {
                ps.setInt(1, 2);
            } else {
                ps.setInt(1, 1);
            }
            ps.setInt(2, blogId);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public ArrayList<User> getAllSeller() {
        ArrayList<User> lseller = new ArrayList<>();

        PreparedStatement ps;
        ResultSet rs;
        String sql = "select* from `users` where `setting_id` = 2;";
        try {
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                lseller.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), "", rs.getInt(9), rs.getInt(10), rs.getInt(11)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }

        return lseller;

    }

    private Setting getSettingById(int aInt) {

        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM swp391.setting where setting_id = ?;";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, aInt);
            rs = ps.executeQuery();
            rs.next();
            return new Setting(rs.getInt(1), rs.getString(2), rs.getString(4));

        } catch (SQLException e) {

        }
        return null;
    }

    public int countBlog() {
        int count;
        try {
            String sql = " select count(*) from `blog`";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
            return count;

        } catch (SQLException e) {

        }
        return 0;
    }

    public ArrayList<Blog> pagingBlogs(int index, int numPage, int role, int userId) {
        ArrayList<Blog> list = new ArrayList<>();
        try {
            if (role == 1) {
                String sql = "SELECT * FROM `blog` order by  post_date desc LIMIT ?, ?;";
                PreparedStatement ps = connection.prepareStatement(sql);
                index = (index - 1) * numPage;
                ps.setInt(1, index);
                ps.setInt(2, numPage);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), getUserById(rs.getInt(4)), rs.getDate(5), rs.getString(6), getSettingById(rs.getInt(7)), rs.getInt(8), rs.getString(9)));
                }
            } else if (role == 2) {
                String sql = "SELECT * FROM `blog` where `user_id`=? order by  post_date desc LIMIT ?, ?;";
                PreparedStatement ps = connection.prepareStatement(sql);
                index = (index - 1) * numPage;
                ps.setInt(1, userId);
                ps.setInt(2, index);
                ps.setInt(3, numPage);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), getUserById(rs.getInt(4)), rs.getDate(5), rs.getString(6), getSettingById(rs.getInt(7)), rs.getInt(8), rs.getString(9)));
                }
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public int countBlogByRole(int setting_id, int userId) {
        int count = countBlog();
        if (setting_id == 2) {

            try {
                    String sql = " select count(*) from `blog` where `user_id`=?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                rs.next();
                count = rs.getInt(1);
                return count;

            } catch (SQLException e) {

            }
        }
        return count;
    }
}
