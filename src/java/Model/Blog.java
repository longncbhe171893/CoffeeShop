/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Blog {

    private int blog_id;
    private String blog_title;
    private String blog_image;
    private User user;
    private Date post_date;
    private String content;
    private Setting setting;
    private int blog_status;
    private String description;
    

    public Blog() {
    }

    public Blog(int blog_id, String blog_title, String blog_image, User user, Date post_date, String content, Setting setting, int blog_status, String description) {
        this.blog_id = blog_id;
        this.blog_title = blog_title;
        this.blog_image = blog_image;
        this.user = user;
        this.post_date = post_date;
        this.content = content;
        this.setting = setting;
        this.blog_status = blog_status;
        this.description = description;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public String getBlog_title() {
        return blog_title;
    }

    public void setBlog_title(String blog_title) {
        this.blog_title = blog_title;
    }

    public String getBlog_image() {
        return blog_image;
    }

    public void setBlog_image(String blog_image) {
        this.blog_image = blog_image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public int getBlog_status() {
        return blog_status;
    }

    public void setBlog_status(int blog_status) {
        this.blog_status = blog_status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Blog{" + "blog_id=" + blog_id + ", blog_title=" + blog_title + ", blog_image=" + blog_image + ", user=" + user + ", post_date=" + post_date + ", content=" + content + ", setting=" + setting + ", blog_status=" + blog_status + ", description=" + description + '}';
    }

    
    
}
