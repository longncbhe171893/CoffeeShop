<<<<<<< HEAD
=======

>>>>>>> 0e50b69a349360feb864dc2a4cc1cb4ae5fce920
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

<<<<<<< HEAD
/**
 *
 * @author anhvu
 */
public class Setting {
    private int setting_id;
    private String setting_name;
    private String type;
=======
public class Setting {

    private int id;
    private String name;
    private String description;
    private String type;
    private int sort;
    private int status;
>>>>>>> 0e50b69a349360feb864dc2a4cc1cb4ae5fce920

    public Setting() {
    }

<<<<<<< HEAD
    public Setting(int setting_id, String setting_name, String type) {
        this.setting_id = setting_id;
        this.setting_name = setting_name;
        this.type = type;
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
=======

    public Setting(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    

    public Setting(int id, String name, String description, String type, int sort) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.sort = sort;
    }

    public Setting(int id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Setting(int id) {
        this.id = id;
    }

    public Setting(String name) {
        this.name = name;
    }

    public Setting(int id, String name) {
        this.id = id;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
>>>>>>> 0e50b69a349360feb864dc2a4cc1cb4ae5fce920
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
<<<<<<< HEAD
    
    
}
=======

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

}
>>>>>>> 0e50b69a349360feb864dc2a4cc1cb4ae5fce920
