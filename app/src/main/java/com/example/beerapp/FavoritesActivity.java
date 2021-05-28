package com.example.beerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
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


        setContentView(R.layout.activity_favorites);
        adapter = new RecViewAdapter(this);
        favoritesRecyclerView = findViewById(R.id.favoriteRecyclerView);

        favoritesRecyclerView.setAdapter(adapter);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList <Beer> beers = new ArrayList<>();

        //only laod 1 beer
        beers.add(new Beer(beerStyles[0], 0, "Short Pipi lolololo", "longPipi lololo", beerImageLinks[0]));

        adapter.setBeers(beers);
    }
}