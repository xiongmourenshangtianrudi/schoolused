package com.schoolused.entry;

import java.util.List;

public class commodity {
    private RegularUsers Regularuser;
    private int com_id;
    private String com_name;
    private float com_price;
    private int com_stock;
    private  String com_type;
    private int com_userid;
    private String util;
    private String com_description;
    private String time;
    private String img;
    private int active;
    private List<String> urls;//这是上传时生成的图片地址，放到imgs数据库钟
    private List<image> images;

    public RegularUsers getRegularuser() {
        return Regularuser;
    }

    public void setRegularuser(RegularUsers regularuser) {
        Regularuser = regularuser;
    }

    public List<image> getImages() {
        return images;
    }

    public void setImages(List<image> images) {
        this.images = images;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUtil() {
        return util;
    }

    public void setUtil(String util) {
        this.util = util;
    }

    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public float getCom_price() {
        return com_price;
    }

    public void setCom_price(float com_price) {
        this.com_price = com_price;
    }

    public int getCom_stock() {
        return com_stock;
    }

    public void setCom_stock(int com_stock) {
        this.com_stock = com_stock;
    }

    public String getCom_type() {
        return com_type;
    }

    public void setCom_type(String com_type) {
        this.com_type = com_type;
    }

    public int getCom_userid() {
        return com_userid;
    }

    public void setCom_userid(int com_userid) {
        this.com_userid = com_userid;
    }

    public String getCom_description() {
        return com_description;
    }

    public void setCom_description(String com_description) {
        this.com_description = com_description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "commodity{" +
                "Regularuser=" + Regularuser +
                ", com_id=" + com_id +
                ", com_name='" + com_name + '\'' +
                ", com_price=" + com_price +
                ", com_stock=" + com_stock +
                ", com_type='" + com_type + '\'' +
                ", com_userid=" + com_userid +
                ", util='" + util + '\'' +
                ", com_description='" + com_description + '\'' +
                ", time='" + time + '\'' +
                ", img='" + img + '\'' +
                ", urls=" + urls +
                ", images=" + images +
                '}';
    }
}
