
package Model;

/**
 *
 *
 */
public class Setting {
    private int setting_id;
    private String name;
    private String description;
    private String type;
    private int sort;

    public Setting() {
    }

    public Setting(int setting_id, String name, String description, String type, int sort) {
        this.setting_id = setting_id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.sort = sort;
    }

    public Setting(int setting_id, String name, String description, String type) {
        this.setting_id = setting_id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Setting(int setting_id, String name) {
        this.setting_id = setting_id;
        this.name = name;
    }

    public Setting(int setting_id) {
        this.setting_id = setting_id;
    }
    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
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
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    
}