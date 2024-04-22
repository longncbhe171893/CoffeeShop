package Model;

public class Setting {

    private int id;
    private String name;
    private String description;
    private String type;
    private int sort;
    private int status;

    public Setting() {
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
