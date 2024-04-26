/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Feedback;
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

    public ArrayList<Feedback> getAllFeedback() {
        ArrayList<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM swp391.Feedback";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                feedbackList.add(new Feedback(
                        rs.getInt("feedback_id"),
                        rs.getInt("user_id"),
                        rs.getString("content"),
                        rs.getInt("product_id"),
                        rs.getDate("post_Date")
                ));

            }
        } catch (SQLException e) {
        }

        return feedbackList;
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
