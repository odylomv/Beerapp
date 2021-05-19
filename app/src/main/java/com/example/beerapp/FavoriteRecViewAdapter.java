package com.example.beerapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.example.beerapp.BeerActivity.BEER_ID_KEY;

public class FavoriteRecViewAdapter extends RecyclerView.Adapter<FavoriteRecViewAdapter.ViewHolder> {
    private static final String TAG = "FavoriteRecViewAdapter";
    private ArrayList<Beer> beers = new ArrayList<>();
    private Context context;

    public FavoriteRecViewAdapter(Context context) {
        this.context = context;
    }

    public void setBeers(ArrayList<Beer> beers) {
        this.beers = beers;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_beer,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FavoriteRecViewAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.beerTitle.setText(beers.get(position).getName());

        Glide.with(context).asBitmap().
                load(beers.get(position).getImgSource())
                .into(holder.beerPic);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BeerActivity.class);
                intent.putExtra(BEER_ID_KEY,beers.get(position).getId());
                context.startActivity(intent);
            }
        });

    }
    public Beer getBeerById(int id) {
        for(Beer beer:beers){
            if(beer.getId() == id){
                return beer;
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView beerPic;
        private TextView beerTitle;
        private TextView beerShortDisc;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            beerPic = itemView.findViewById(R.id.beerPic);
            beerTitle = itemView.findViewById(R.id.beerTitle);
            beerShortDisc = itemView.findViewById(R.id.beerShortDescription);

        }
    }

}
