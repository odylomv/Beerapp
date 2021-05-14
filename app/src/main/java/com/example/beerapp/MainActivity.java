package com.example.beerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button favButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        favButton = findViewById(R.id.buttonFavorites);
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FavoritesActivity.class);
                startActivity(intent);
            }
        });
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