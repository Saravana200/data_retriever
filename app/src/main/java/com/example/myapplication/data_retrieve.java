package com.example.myapplication;

public class data_retrieve {
    String desc;
    String image_url;

    public data_retrieve(String desc, String image_url) {
        this.desc = desc;
        this.image_url = image_url;
    }

    public data_retrieve() {
        desc=image_url=null;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
