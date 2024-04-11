/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author asus
 */
public class Product {
    private int id; 
    private String name; 
    private double price; 
    private String image; 
    private Setting category; 
    private String decription; 
    private int status ; 
    private Date createDate; 

    public Product() {
    }
    
    public Product(int id, String name, double price, String image, Setting category, String decription, int status, Date createDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
        this.decription = decription;
        this.status = status;
        this.createDate = createDate;
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(int id) {
        this.id = id;
    }
    
 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Setting getCategory() {
        return category;
    }

    public void setCategory(Setting category) {
        this.category = category;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
   
}
