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

        ArrayList <Beer> beers = new ArrayList<>();
        ArrayList<Integer> beerIds = new ArrayList<>();
        Utilities utilities = new Utilities(FavoritesActivity.this);
        beerIds = utilities.getFavorites();
        for(Integer i : beerIds){
            beers.add(new Beer(beerStyles[i],i,beerShortDesc[i], " ", beerImageLinks[i]));
        }

        //only load 1 beer
        //beers.add(new Beer(beerStyles[0], 0, beerShortDesc[0], beerShortDesc[0], beerImageLinks[0])); //temporarily using short desc as long, needs to change

        adapter.setBeers(beers);
    }
}