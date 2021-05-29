package com.example.beerapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;

public class BeerActivity extends AppCompatActivity {
    public static final String BEER_ID_KEY = "beerId";
    private TextView aloneBeerName, aloneLongDesc;
    private ImageView aloneBeerPic;
    private Button addToFavBtn;
    private ImageButton addToFvHeart;
    private boolean isfavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);

        aloneBeerName = findViewById(R.id.aloneBeerName);
        aloneBeerPic = findViewById(R.id.aloneBeerpic);
        aloneLongDesc = findViewById(R.id.aloneLongDesc);
        addToFavBtn = findViewById(R.id.addtoFavBtn);
        addToFvHeart = findViewById(R.id.addToFvHeart);


        //Creating action bar
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#e2d1c3"));
        actionBar.setBackgroundDrawable(colorDrawable);

        //Beer beer = new Beer("Pipi", 0, "Short Pipi lolololo", "longPipi lololo", "https://www.csd.auth.gr/wp-content/uploads/2019/08/Tefas2020small-240x300.jpg");

        Beer beer = new Beer();
        Intent intent = getIntent();
        //This is where we get each beer's content to display on Beer Activity using beerId and string arrays for content
        Resources res = getResources();
        String[] beerStyles = res.getStringArray(R.array.beerStyles);
        String [] beerImageLinks = res.getStringArray(R.array.beerImageLinks);
        String[] beerShortDesc = res.getStringArray(R.array.shortDescriptions);

        if (intent != null) {
            int beerId = intent.getIntExtra(BEER_ID_KEY, -1); //TODO open specific beer for each tap create utilities class, load both short and long desc
            beer = new Beer(beerStyles[beerId],beerId,beerShortDesc[beerId],beerShortDesc[beerId] ,beerImageLinks[beerId]); //using short desc as long temporarily
        }

        addToFvHeart.setOnClickListener(new View.OnClickListener() {
            /**
             * This is only temporary till db is done
             */
            @Override
            public void onClick(View view) {
                if(!isfavorite){
                    addToFvHeart.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                    isfavorite = true;
                }
                else{
                    addToFvHeart.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24);
                    isfavorite = false;
                }
            }
        });


        setData(beer);
    }

    private void setData(Beer beer) {
        aloneLongDesc.setText(beer.getLongDescription());
        aloneBeerName.setText(beer.getName());
        Glide.with(this).asBitmap().load(beer.getImgSource()).into(aloneBeerPic);

    }

}