/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Feedback;
import Model.Product;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class FeedbackDAO extends DBContext {

    public List<Feedback> getAllFeedback() {
        String query = "SELECT f.feedback_id, f.user_id, f.content, f.product_id, u.user_name, p.product_name "
                + "FROM Feedback f "
                + "INNER JOIN Users u ON f.user_id = u.user_id "
                + "INNER JOIN Product p ON f.product_id = p.product_id";
        List<Feedback> feedbacks = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int feedback_id = resultSet.getInt("feedback_id");
                int user_id = resultSet.getInt("user_id");
                String content = resultSet.getString("content");
                int product_id = resultSet.getInt("product_id");
                String user_name = resultSet.getString("user_name");
                String product_name = resultSet.getString("product_name");

                User user = new User();
                user.setId(user_id);
                user.setName(user_name);

                Product product = new Product();
                product.setId(product_id);
                product.setName(product_name);

                Feedback feedback = new Feedback();
                feedback.setFeedback_id(feedback_id);
                feedback.setUser_id(user_id);
                feedback.setContent(content);
                feedback.setProduct_id(product_id);
                feedback.setUser(user);
                feedback.setProduct(product);

                feedbacks.add(feedback);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbacks;
    }

    public Feedback getFeedbackDetail(int feedbackId) {
        Feedback feedback = null;
        String sql = "SELECT * FROM Feedback WHERE feedback_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, feedbackId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                feedback = new Feedback(
                        rs.getInt("feedback_id"),
                        rs.getInt("user_id"),
                        rs.getString("content"),
                        rs.getInt("product_id"),
                        rs.getDate("post_Date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedback;
    }

    public static void main(String[] args) {
        FeedbackDAO fe = new FeedbackDAO();
        List<Feedback> listP = fe.getAllFeedback();
        System.out.println(listP);

    }

    public ArrayList<Feedback> searchFeedback(String search) {
        ArrayList<Feedback> list = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM `Feedback` f "
                + "JOIN `User` u ON f.`user_id` = u.`user_id` "
                + "WHERE u.`name` LIKE ? "
                + "ORDER BY f.`feedback_id` ASC;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Feedback(
                        rs.getInt("feedback_id"),
                        rs.getInt("user_id"),
                        rs.getString("content"),
                        rs.getInt("product_id"),
                        rs.getDate("post_Date")));
            }
        } catch (Exception e) {
        }
        return list;
    }

}
