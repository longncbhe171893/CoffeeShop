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
    private String descreption;
    private int product_status;
    private Date create_date;
    private int size;
    private int quantity;
    

    public Product() {
    }

    public Product(int id, String name, double price, String image, String descreption, int product_status, Date create_date, int size, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.descreption = descreption;
        this.product_status = product_status;
        this.create_date = create_date;
        this.size = size;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product(int id, String name, double price, int setting_id, String image, String descreption, int product_status, Date create_date, int size) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.setting_id = setting_id;
        this.image = image;
        this.descreption = descreption;
        this.product_status = product_status;
        this.create_date = create_date;
        this.size = size;
    }

    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
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

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public int getProduct_status() {
        return product_status;
    }

    public void setProduct_status(int product_status) {
        this.product_status = product_status;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + '}';
    }

   
    
    
    
}
