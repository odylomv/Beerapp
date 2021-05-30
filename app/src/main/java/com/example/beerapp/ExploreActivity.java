package com.example.beerapp;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExploreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

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
        RecyclerView exploreRecyclerView = findViewById(R.id.exploreRecyclerView);

        exploreRecyclerView.setAdapter(adapter);
        exploreRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Beer> beers = new ArrayList<>();

        for(int i = 0; i < beerStyles.length; i++)
            beers.add(new Beer(beerStyles[i], i, beerShortDesc[i], " ", beerImageLinks[i]));

        adapter.setBeers(beers);
    }
}