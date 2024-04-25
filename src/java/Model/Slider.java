/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Slider {
    private int slider_id;
    private String title;
    private String img;
    private String url;
    private int status;

    public Slider() {
    }

    public Slider(int slider_id, String title, String img, String url, int status) {
        this.slider_id = slider_id;
        this.title = title;
        this.img = img;
        this.url = url;
        this.status = status;
    }

    public int getSlider_id() {
        return slider_id;
    }

    public void setSlider_id(int slider_id) {
        this.slider_id = slider_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Slider{" + "slider_id=" + slider_id + ", title=" + title + ", img=" + img + ", url=" + url + ", status=" + status + '}';
    }
    
}
