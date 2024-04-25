package Model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Order {

    private int id;
    private User user;
    private String orderName;
    private int status;
    private int discount;
    private Timestamp date;
    private String notes;
    private String address;
    private String phone;
    private String seller_approve;
    private ArrayList<OrderDetail> orderdetails; 

    public Order() {
    }

    public Order(int id, User user, String orderName, int status, int discount, Timestamp date, String notes, String address, String phone, String seller_approve) {
        this.id = id;
        this.user = user;
        this.orderName = orderName;
        this.status = status;
        this.discount = discount;
        this.date = date;
        this.notes = notes;
        this.address = address;
        this.phone = phone;
        this.seller_approve = seller_approve;
    }


    public String getSeller_approve() {
        return seller_approve;
    }

    public void setSeller_approve(String seller_approve) {
        this.seller_approve = seller_approve;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    

   

    public String formatDate() {
        Timestamp date = this.getDate();
        // Lấy ngày, tháng, năm từ đối tượng Date
        String day = String.format("%td", date);
        String month = String.format("%tm", date);
        String year = String.format("%tY", date);
        // Lấy giờ, phút từ đối tượng Date
        String hour = String.format("%tH", date);
        String minute = String.format("%tM", date);
        // Định dạng lại ngày, tháng, năm, giờ, phút thành chuỗi "dd-MM-yyyy HH:mm"
        return day + "-" + month + "-" + year + " " + hour + ":" + minute;
    }

    public String formatDate1() {
        Timestamp timestamp = this.getDate();
        // Định dạng theo "dd-MM-yyyy HH:mm"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String formattedDate = dateFormat.format(timestamp);
        return formattedDate;
    }

    public ArrayList<OrderDetail> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(ArrayList<OrderDetail> orderdetails) {
        this.orderdetails = orderdetails;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", user=" + user + ", orderName=" + orderName + ", status=" + status + ", discount=" + discount + ", date=" + date + ", notes=" + notes + ", address=" + address + ", phone=" + phone + ", orderdetails=" + orderdetails + '}';
    }
    
    
}
