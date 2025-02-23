package com.schoolused.entry;

public class indexMapUrl {
    private int id;
    private  String url;
    private String adurl;

    public indexMapUrl(){
        this.id = 0;
        this.url = null;
    }
    public indexMapUrl(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAdurl() {
        return adurl;
    }

    public void setAdurl(String adurl) {
        this.adurl = adurl;
    }
}
