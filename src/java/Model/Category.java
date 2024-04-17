/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author asus
 */
public class Category {

    private int id;
    private String name;
    private int setting_id;

    public Category() {
    }

    public Category(int id, String name, int setting_id) {
        this.id = id;
        this.name = name;
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

    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + ", setting_id=" + setting_id + '}';
    }

    
}
