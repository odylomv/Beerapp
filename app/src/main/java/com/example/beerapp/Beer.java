package com.example.beerapp;

/**
 * This class is used to represent a beer item, with the following attributes
 * Id the beer id, name its name, short and long description are self explanatory
 * and imgSource the destination of each beer's picture
 */
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

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
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

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
}