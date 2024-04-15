/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author asus
 */
public class OrderDetail {

    private int id;
    private Order order;
    private Product product;
    private double orderPrice;
    private int quanlity;
    private double amount;
    public OrderDetail() {
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "id=" + id + ", order=" + order + ", product=" + product + ", orderPrice=" + orderPrice + ", quanlity=" + quanlity + '}';
    }

    public OrderDetail(int id, Order order, Product product, double orderPrice, int quanlity, double amount) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.orderPrice = orderPrice;
        this.quanlity = quanlity;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

   
    
}
