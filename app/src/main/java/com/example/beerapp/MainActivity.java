package com.example.beerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBeerCarousel (View view){
        Intent intent = new Intent(this, BeerCarousel.class);
        startActivity(intent);
    }

    public void openAbout(View view){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }
}