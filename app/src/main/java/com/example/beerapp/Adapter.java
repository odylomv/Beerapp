package com.example.beerapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.jetbrains.annotations.NotNull;

public class Adapter extends PagerAdapter {

    private Context context;
    private int[] imageArray = new int[]{R.drawable.beer1,R.drawable.beer2,R.drawable.beer3};

    Adapter(Context ctx){context=ctx;}


    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view==object;
    }


    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(imageArray[position]);
        container.addView(imageView,0);
        return imageView;
    }
}
