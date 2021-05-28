package com.example.beerapp;

import android.content.res.Resources;
import android.os.Bundle;

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


        setContentView(R.layout.activity_explore);
        adapter = new RecViewAdapter(this);
        exploreRecyclerView = findViewById(R.id.exploreRecyclerView);

        exploreRecyclerView.setAdapter(adapter);
        exploreRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Beer> beers = new ArrayList<>();

        for(int i=0; i<beerStyles.length;i++)
            beers.add(new Beer(beerStyles[i], i, "tasos tefas", "taaaaaaaasos teeeeeeeeeefas", beerImageLinks[i]));

        adapter.setBeers(beers);
    }
}