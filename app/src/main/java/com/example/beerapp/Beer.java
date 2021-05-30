package com.example.beerapp;

public class Beer {
    private int id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String imgSource;

    public Beer() { }

    public Beer(String name, int id, String shortDescription, String longDescription, String imgSource) {
        this.name = name;
        this.id = id;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.imgSource = imgSource;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getImgSource() {
        return imgSource;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }
}