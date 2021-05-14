package com.example.beerapp;

import android.content.Context;
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
                Toast.makeText(context, beers.get(position).getName() + " selected", Toast.LENGTH_SHORT).show();
            }
        });

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
