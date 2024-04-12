/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class Setting {
    private int settingId;
    private String settingName;
    private String description;
    private String type;
    private int settingSort;

    public Setting() {
    }
    
    

    public Setting(int settingId, String settingName, String description, String type, int settingSort) {
        this.settingId = settingId;
        this.settingName = settingName;
        this.description = description;
        this.type = type;
        this.settingSort = settingSort;
    }

    public int getSettingId() {
        return settingId;
    }

    public void setSettingId(int settingId) {
        this.settingId = settingId;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSettingSort() {
        return settingSort;
    }

    public void setSettingSort(int settingSort) {
        this.settingSort = settingSort;
    }
    
}
