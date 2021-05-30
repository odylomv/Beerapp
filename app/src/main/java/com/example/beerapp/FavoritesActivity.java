package com.example.beerapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Resources res = getResources();
        String[] beerStyles = res.getStringArray(R.array.beerStyles);
        String[] beerImageLinks = res.getStringArray(R.array.beerImageLinks);
        String[] beerShortDesc = res.getStringArray(R.array.shortDescriptions);
        String[] beerLongDesc = res.getStringArray(R.array.longDescriptions);

        // Fetch ActionBar instance
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#e2d1c3"));
            actionBar.setBackgroundDrawable(colorDrawable);
        }

        RecViewAdapter adapter = new RecViewAdapter(this);
        RecyclerView favoritesRecyclerView = findViewById(R.id.favoriteRecyclerView);

        favoritesRecyclerView.setAdapter(adapter);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get all favorite beers and add them to the list
        ArrayList <Beer> beers = new ArrayList<>();
        Utilities utilities = new Utilities(FavoritesActivity.this);
        for(Integer i : utilities.getFavorites())
            beers.add(new Beer(beerStyles[i], i, beerShortDesc[i], beerLongDesc[i], beerImageLinks[i]));

        adapter.setBeers(beers);
    }
}