package com.example.beerapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button exploreButton;
    private Button favButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#e2d1c3"));
        actionBar.setBackgroundDrawable(colorDrawable);

        Resources res = getResources();
        String[] beerStyles = res.getStringArray(R.array.beerStyles);
        String [] beerImageLinks = res.getStringArray(R.array.beerImageLinks);


        exploreButton = findViewById(R.id.buttonExplore);
        exploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExploreActivity.class);
                startActivity(intent);
            }
        });


        favButton = findViewById(R.id.buttonFavorites);
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FavoritesActivity.class);
                startActivity(intent);
            }
        });

    }

    public void openAbout(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

}