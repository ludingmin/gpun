package com.gpnu.pojo;

public class Images {
    private int id;
    private String url;

    private String name;

    public String getName() {
        return name;
    }

    public Images(int id, String url, String name) {
        this.id = id;
        this.url = url;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Images(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public Images() {
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
}
