package com.example.beerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BeerActivity extends AppCompatActivity {
    public static final String BEER_ID_KEY = "beerId";
    private TextView aloneBeerName, aloneLongDesc;
    private ImageView aloneBeerPic;
    private Button addToFavBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        aloneBeerName = findViewById(R.id.aloneBeerName);
        aloneBeerPic = findViewById(R.id.aloneBeerpic);
        aloneLongDesc = findViewById(R.id.aloneLongDesc);
        addToFavBtn = findViewById(R.id.addtoFavBtn);
        //Beer beer = new Beer("Pipi", 0, "Short Pipi lolololo", "longPipi lololo", "https://www.csd.auth.gr/wp-content/uploads/2019/08/Tefas2020small-240x300.jpg");
        Beer beer = new Beer();
        Intent intent = getIntent();

        Resources res = getResources();
        String[] beerStyles = res.getStringArray(R.array.beerStyles);
        String [] beerImageLinks = res.getStringArray(R.array.beerImageLinks);
        if (intent != null) {
            int beerId = intent.getIntExtra(BEER_ID_KEY, -1); //TODO open specific beer for each tap create utilities class
            beer = new Beer(beerStyles[beerId],beerId,"This could be a short pipi", "This could be looong pipi",beerImageLinks[beerId]);
        }

        setData(beer);
    }

    private void setData(Beer beer) {
        aloneLongDesc.setText(beer.getLongDescription());
        aloneBeerName.setText(beer.getName());
        Glide.with(this).asBitmap().load(beer.getImgSource()).into(aloneBeerPic);

    }

}