package com.example.beerapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class Adapter extends PagerAdapter {
    private final Context context;
    //hard coded, probably best to initialize with a for loop
    private final int[] imageArray = { R.drawable.beer1, R.drawable.beer2, R.drawable.beer3, R.drawable.beer4,
            R.drawable.beer5,  R.drawable.beer6, R.drawable.beer7, R.drawable.beer8, R.drawable.beer9,
            R.drawable.beer10, R.drawable.beer11, R.drawable.beer12, R.drawable.beer13, R.drawable.beer14 };

    private ImageView[] beerViews = new ImageView[imageArray.length];

    public Adapter(Context ctx) {
        context = ctx;
    }

    @Override
    public int getCount() {
        return imageArray.length * 100;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int relativePos = position % imageArray.length;

        if (beerViews[relativePos] == null) {
            beerViews[relativePos] = new ImageView(context);
            beerViews[relativePos].setScaleType(ImageView.ScaleType.CENTER_CROP);
            beerViews[relativePos].setImageResource(imageArray[relativePos]);
        }
        container.addView(beerViews[relativePos],0);

        return beerViews[relativePos];
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //container.removeView(container.getRootView());
        container.removeView((ImageView) object);
    }
}