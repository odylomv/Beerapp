package com.example.beerapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#e2d1c3"));
            actionBar.setBackgroundDrawable(colorDrawable);
        }

        Resources res = getResources();
        String[] beerStyles = res.getStringArray(R.array.beerStyles);
        String [] beerImageLinks = res.getStringArray(R.array.beerImageLinks);

        Button exploreButton = findViewById(R.id.buttonExplore);
        exploreButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ExploreActivity.class);
            startActivity(intent);
        });

        Button favButton = findViewById(R.id.buttonFavorites);
        favButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
            startActivity(intent);
        });

        Button aboutButton = findViewById(R.id.buttonAbout);
        aboutButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });

        // Creating the carousel on the main page
        ViewPager viewPager = findViewById(R.id.viewPager);
        Adapter adapter = new Adapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(adapter.getCount() / 2, false);

        findViewById(R.id.leftArrow).setOnClickListener(view -> {
            if (viewPager.getCurrentItem() > 0)
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        });
        findViewById(R.id.rightArrow).setOnClickListener(view -> {
            if (viewPager.getCurrentItem() + 1 < Objects.requireNonNull(viewPager.getAdapter()).getCount())
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        });
    }
}