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
    private RecyclerView favoritesRecyclerView;
    private RecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources res = getResources();
        String[] beerStyles = res.getStringArray(R.array.beerStyles);
        String [] beerImageLinks = res.getStringArray(R.array.beerImageLinks);
        String [] beerShortDesc = res.getStringArray(R.array.shortDescriptions);

        //Creating action bar
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#e2d1c3"));
        actionBar.setBackgroundDrawable(colorDrawable);


        setContentView(R.layout.activity_favorites);
        adapter = new RecViewAdapter(this);
        favoritesRecyclerView = findViewById(R.id.favoriteRecyclerView);

        favoritesRecyclerView.setAdapter(adapter);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList <Beer> beers = new ArrayList<>();

        //only laod 1 beer
        beers.add(new Beer(beerStyles[0], 0, beerShortDesc[0], beerShortDesc[0], beerImageLinks[0])); //temporarily using short desc as long, needs to change

        adapter.setBeers(beers);
    }
}