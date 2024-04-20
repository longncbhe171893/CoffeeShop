/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class setting {
    private int setting_id;
    private String setting_name;
    private String description;
    private String type;
    private int sort;
    private int status;
    public setting() {
    }

    public setting(int setting_id, String setting_name, String type) {
        this.setting_id = setting_id;
        this.setting_name = setting_name;
        this.type = type;
    }

    public setting(int setting_id, String setting_name, String description, String type) {
        this.setting_id = setting_id;
        this.setting_name = setting_name;
        this.description = description;
        this.type = type;
        
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public String getSetting_name() {
        return setting_name;
    }

    public void setSetting_name(String setting_name) {
        this.setting_name = setting_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
