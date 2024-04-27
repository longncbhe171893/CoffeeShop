/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Feedback {
    private int feedback_id;
     private int user_id;
     private String content;
     private int product_id;
     private User user;
     private Product product;
     private Date post_Date;
     private String note;

    public Feedback() {
    }

    public Feedback(int feedback_id, int user_id, String content, int product_id, Date post_Date, String note) {
        this.feedback_id = feedback_id;
        this.user_id = user_id;
        this.content = content;
        this.product_id = product_id;
        this.post_Date = post_Date;
        this.note = note;
    }

    

    

    public Feedback(int feedback_id, int user_id, String content, int product_id, User user, Product product, Date post_Date, String note) {
        this.feedback_id = feedback_id;
        this.user_id = user_id;
        this.content = content;
        this.product_id = product_id;
        this.user = user;
        this.product = product;
        this.post_Date = post_Date;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    

    
    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Date getPost_Date() {
        return post_Date;
    }

    public void setPost_Date(Date post_Date) {
        this.post_Date = post_Date;
    }

    @Override
    public String toString() {
        return "Feedback{" + "feedback_id=" + feedback_id + ", user_id=" + user_id + ", content=" + content + ", product_id=" + product_id + ", user=" + user + ", product=" + product + ", post_Date=" + post_Date + ", note=" + note + '}';
    }

    

    
    
     
}
