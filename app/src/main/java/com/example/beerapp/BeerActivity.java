package com.example.beerapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


// Used to represent a standalone beer after the user has clicked on a beer in explore or favorites
public class BeerActivity extends AppCompatActivity implements ModalForComment.ModalCommentListener {
    public static final String BEER_ID_KEY = "beerId";
    private TextView aloneBeerName, aloneLongDesc, displayComment;
    private CardView commentCard;
    private ImageView aloneBeerPic;
    private ImageButton addToFvHeart;
    private boolean isFavorite;
    private int beerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        //initialize fields
        aloneBeerName = findViewById(R.id.aloneBeerName);
        aloneBeerPic = findViewById(R.id.aloneBeerpic);
        aloneLongDesc = findViewById(R.id.aloneLongDesc);
        addToFvHeart = findViewById(R.id.addToFvHeart);
        FloatingActionButton commentFAB = findViewById(R.id.commentFAB);
        commentCard = findViewById(R.id.commentCard);
        displayComment = findViewById(R.id.commentDisplay);
        //FAB for adding comment
        commentFAB.setOnClickListener(view -> openDialog());

        // Fetch ActionBar instance
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#e2d1c3"));
            actionBar.setBackgroundDrawable(colorDrawable);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //This is where we get each beer's content to display on Beer Activity using beerId and string arrays for content
        Beer beer = new Beer();
        Resources res = getResources();
        String[] beerStyles = res.getStringArray(R.array.beerStyles);
        String[] beerImageLinks = res.getStringArray(R.array.beerImageLinks);
        String[] beerShortDesc = res.getStringArray(R.array.shortDescriptions);
        String[] beerLongDesc = res.getStringArray(R.array.longDescriptions);

        beerId = -1;
        Intent intent = getIntent();
        if (intent != null) {
            beerId = intent.getIntExtra(BEER_ID_KEY, -1);
            beer = new Beer(beerStyles[beerId], beerId, beerShortDesc[beerId], beerLongDesc[beerId], beerImageLinks[beerId]); //using short desc as long temporarily
        }

        /* this is supposed to check favorite status to display the appropriate heart*/
        Utilities utilities = new Utilities(BeerActivity.this);
        ArrayList<Integer> favs;

        favs = utilities.getFavorites();
        isFavorite = favs.contains(beerId);
        if (isFavorite) {
            addToFvHeart.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
        } else {
            addToFvHeart.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24);
        }

        int finalBeerId = beerId;
        addToFvHeart.setOnClickListener(view -> { //here we add/remove from favorites using SQLite
            if (!isFavorite) {
                addToFvHeart.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                utilities.addFav(finalBeerId);
                Toast.makeText(BeerActivity.this, "Added to favorites", Toast.LENGTH_SHORT).show();
            } else {
                System.out.println("About to delete");
                Toast.makeText(BeerActivity.this, "Removing from favorites", Toast.LENGTH_SHORT).show();
                addToFvHeart.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24);
                utilities.deleteFav(finalBeerId);
            }
            isFavorite = !isFavorite;
        });

        setData(beer);

        //This is where we load comment if it exists
        ArrayList<Integer> commented = utilities.getCommented();
        if (!commented.contains(beerId)) {
            commentCard.setVisibility(View.INVISIBLE);
        } else {
            displayComment.setText(utilities.getComment(beerId));
            commentCard.setVisibility(View.VISIBLE);
        }
    }

    public void openDialog() {
        ModalForComment modal = new ModalForComment(beerId);
        modal.show(getSupportFragmentManager(), "comment");
    }

    private void setData(Beer beer) {
        aloneLongDesc.setText(beer.getLongDescription());
        aloneBeerName.setText(beer.getName());
        Glide.with(this).asBitmap().load(beer.getImgSource()).into(aloneBeerPic);
    }

    @Override //Used to change the data in the COMMENTS table, delete, add or update
    public void applyTexts(String comment,String state) {
        displayComment.setText(comment);
        commentCard.setVisibility(View.VISIBLE);
        Utilities db = new Utilities(BeerActivity.this);
        if (comment.isEmpty()) {
            Toast.makeText(BeerActivity.this, "Empty comments will be deleted automatically", Toast.LENGTH_SHORT).show();
            db.deleteComment(beerId);
            commentCard.setVisibility(View.INVISIBLE);
        }
        else {
            if (state.equals("NEW")) {
                db.addComment(beerId, comment);
                Toast.makeText(BeerActivity.this, "New comment added", Toast.LENGTH_SHORT).show();
            }
            else {
                db.updateComment(beerId, comment);
                Toast.makeText(BeerActivity.this, "Existing comment modified", Toast.LENGTH_SHORT).show();
            }

        }
    }

    // This hack is needed to properly navigate back to the
    // correct Activity using the ActionBar back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}