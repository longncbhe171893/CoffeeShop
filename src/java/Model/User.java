/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author asus
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String sex;
    private String image;
    private Setting role;
    private int status;
    private double point; 

    public User() {
    }    
    
    public User(String name) {
        this.name = name;
    }

    public User(int id, String name, String email, String password, String address,String phone, String sex,String image, Setting role, int status, double point) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address=address;
        this.phone=phone;
        this.sex=sex;
        this.image=image;
        this.role = role;
        this.status = status;
        this.point = point;
    } 

    public User(String name, String email, Setting role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public User(String name, Setting role) {
        this.name = name;
        this.role = role;
    }

    public User(int id, String name, String email, Setting role, int status, double point) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
        this.point = point;
    }
     
    public User(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
      public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
      public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public Setting getRole() {
        return role;
    }

    public void setRole(Setting role) {
        this.role = role;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", address=" + address + ", phone=" + phone + ", sex=" + sex + ", image=" + image + ", role=" + role + ", status=" + status + ", point=" + point + '}';
    }
    
}