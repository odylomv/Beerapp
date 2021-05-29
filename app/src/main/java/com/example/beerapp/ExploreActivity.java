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
    private RecyclerView exploreRecyclerView;
    private RecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources res = getResources();
        String[] beerStyles = res.getStringArray(R.array.beerStyles);
        String [] beerImageLinks = res.getStringArray(R.array.beerImageLinks);
        String[] beerShortDesc = res.getStringArray(R.array.shortDescriptions);
        String[] beerLongDesc = res.getStringArray(R.array.longDescriptions);

        //Creating action bar
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#e2d1c3"));
        actionBar.setBackgroundDrawable(colorDrawable);


        setContentView(R.layout.activity_explore);
        adapter = new RecViewAdapter(this);
        exploreRecyclerView = findViewById(R.id.exploreRecyclerView);

        exploreRecyclerView.setAdapter(adapter);
        exploreRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Beer> beers = new ArrayList<>();

        for(int i=0; i<beerStyles.length;i++)
            beers.add(new Beer(beerStyles[i], i, beerShortDesc[i], beerLongDesc[i], beerImageLinks[i]));

        adapter.setBeers(beers);
    }
}