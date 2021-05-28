package com.example.beerapp;

import java.util.ArrayList;

public class Utilities {

    private static Utilities instance;

    private Utilities() {
    }

    private static ArrayList<Beer> allBeers;
    public static Utilities getInstance(){
        if (null == instance) {
            instance = new Utilities();
        }
        return instance;
    }
}