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
    private int orderId;
    private Product product;
    private double orderPrice;
    private int quanlity;
    private int psId;
    private String orderName;
    private int order_status;
    private Date orderDate;
    private int quantity;
    private String notes;
    private String orderAddress;
    private String orderPhone;
    private double amount;
    private int userId;
    private String productSizeName;
    private int discount; 
    public OrderDetail() {
    }

    public OrderDetail(int id, int orderId, Product product, double orderPrice, int quanlity, int psId) {
        this.id = id;
        this.orderId = orderId;
        this.product = product;
        this.orderPrice = orderPrice;
        this.quanlity = quanlity;
        this.psId = psId;
    }

    public OrderDetail(int orderId, String orderName, int order_status, Date orderDate, Product product, String productSizeName, double orderPrice, int quantity,int discount ,String notes, String orderAddress, String orderPhone, double amount, int userId) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.order_status = order_status;
        this.orderDate = orderDate;
        this.product = product;
        this.productSizeName = productSizeName;
        this.orderPrice = orderPrice;
        this.quanlity = quantity;
        this.discount = discount;
        this.notes = notes;
        this.orderAddress = orderAddress;
        this.orderPhone = orderPhone;
        this.amount = amount;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public int getPsId() {
        return psId;
    }

    public void setPsId(int psId) {
        this.psId = psId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProductSizeName() {
        return productSizeName;
    }

    public void setProductSizeName(String productSizeName) {
        this.productSizeName = productSizeName;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    
}
