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

        // Navigate to Explore page
        findViewById(R.id.buttonExplore).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ExploreActivity.class);
            startActivity(intent);
        });

        // Navigate to Favorites page
        findViewById(R.id.buttonFavorites).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
            startActivity(intent);
        });

        // Navigate to About page
        findViewById(R.id.buttonAbout).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });

        // Create the Carousel on the Main page
        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        viewPager.setAdapter(imageAdapter);
        // Move to the middle of the Carousel for seemingly infinite bi-directional scroll
        viewPager.setCurrentItem(imageAdapter.getCount() / 2, false);

        // Scroll through Carousel using the arrows
        findViewById(R.id.leftArrow).setOnClickListener(view -> {
            if (viewPager.getCurrentItem() > 0)
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        });

        findViewById(R.id.rightArrow).setOnClickListener(view -> {
            if (viewPager.getCurrentItem() + 1 < imageAdapter.getCount())
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        });
    }
}