package com.example.moviedbproject;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Utility {
    public static void loadImageUsingGlide(Context context, ImageView view, String url) {
        if (url != null && !url.isEmpty() && view != null && context != null) {
            Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .into(view);
        }
    }

}
