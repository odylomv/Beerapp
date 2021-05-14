package com.example.beerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {
    private RecyclerView favoritesRecyclerView;
    private FavoriteRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        adapter = new FavoriteRecViewAdapter(this);
        favoritesRecyclerView = findViewById(R.id.favoriteRecyclerView);

        favoritesRecyclerView.setAdapter(adapter);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList <Beer> beers = new ArrayList<>();
        beers.add(new Beer("Pipi",0,"Short Pipi lolololo","longPipi lololo","https://www.csd.auth.gr/wp-content/uploads/2019/08/Tefas2020small-240x300.jpg"));
        beers.add(new Beer("Pipi",0,"Short Pipi lolololo","longPipi lololo","https://www.csd.auth.gr/wp-content/uploads/2019/08/Tefas2020small-240x300.jpg"));
        adapter.setBeers(beers);
    }
}