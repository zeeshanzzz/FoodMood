package com.example.khan.foodmood.Model;

public class Items {
private String name;
private String Url;

    public Items(String name, String url) {
        this.name = name;
        Url = url;
    }

    public Items() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}

