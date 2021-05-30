package com.example.beerapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class Adapter extends PagerAdapter {
    private final Context context;
    private final int[] imageArray = new int[] { R.drawable.beer1, R.drawable.beer2, R.drawable.beer3 };

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
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(imageArray[position % 3]);
        container.addView(imageView,0);

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(container.getRootView());
    }
}