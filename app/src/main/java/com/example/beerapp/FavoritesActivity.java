package com.example.beerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {
    private RecyclerView favoritesRecyclerView;
    private FavoriteRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources res = getResources();
        String[] beerStyles = res.getStringArray(R.array.beerStyles);
        String [] beerImageLinks = res.getStringArray(R.array.beerImageLinks);


        setContentView(R.layout.activity_favorites);
        adapter = new FavoriteRecViewAdapter(this);
        favoritesRecyclerView = findViewById(R.id.favoriteRecyclerView);

        favoritesRecyclerView.setAdapter(adapter);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList <Beer> beers = new ArrayList<>();

        for(int i=0; i<beerStyles.length;i++) { //test loading all beers
            beers.add(new Beer(beerStyles[i], i, "Short Pipi lolololo", "longPipi lololo", beerImageLinks[i]));
        }
        adapter.setBeers(beers);
    }
}