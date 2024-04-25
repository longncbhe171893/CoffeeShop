/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class Contact {
    private int contact_id;
    private String name;
    private String email;
    private String phone;
    private String subject;
    private String message;
    private int setting_id;
    private Setting setting;
    private int user_id;
    private int status;
    private String note;

    public Contact() {
    }

    public Contact(int contact_id, String name, String email, String phone, String subject, String message, int setting_id, int user_id, int status, String note) {
        this.contact_id = contact_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.subject = subject;
        this.message = message;
        this.setting_id = setting_id;   
        this.user_id = user_id;
        this.status = status;
        this.note = note;
    }

    public Contact(int contact_id, String name, String email, String phone, String subject, String message, int setting_id, Setting setting, int user_id, int status, String note) {
        this.contact_id = contact_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.subject = subject;
        this.message = message;
        this.setting_id = setting_id;
        this.setting = setting;
        this.user_id = user_id;
        this.status = status;
        this.note = note;
    }
    
    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    @Override
    public String toString() {
        return "Contact{" + "contact_id=" + contact_id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", subject=" + subject + ", message=" + message + ", setting_id=" + setting_id + ", setting=" + setting + ", user_id=" + user_id + ", status=" + status + ", note=" + note + '}';
    }
    
    
}