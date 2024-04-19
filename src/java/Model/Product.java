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
    private int setting_id;
    private String image; 
    private String decription; 
    private int productStatus ; 
    private Date createDate; 
    private int size;
    public Product() {
    }

    public Product(int id, String name, double price, int setting_id, String image, String decription, int productStatus, Date createDate, int size) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.setting_id = setting_id;
        this.image = image;
        this.decription = decription;
        this.productStatus = productStatus;
        this.createDate = createDate;
        this.size = size;
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

    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", setting_id=" + setting_id + ", image=" + image + ", decription=" + decription + ", productStatus=" + productStatus + ", createDate=" + createDate + ", size=" + size + '}';
    }
}
