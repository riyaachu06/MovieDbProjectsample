package com.example.moviedbproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;

import com.example.moviedbproject.databinding.ActivityMain2Binding;
import com.example.moviedbproject.modelclass.Result;

import static com.example.moviedbproject.MainActivity.ITEM;

public class Main2Activity extends AppCompatActivity {
    ActivityMain2Binding activityMain2Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMain2Binding= DataBindingUtil.setContentView(this,R.layout.activity_main2);
        Intent intent = getIntent();
        Result clickedItem = intent.getParcelableExtra(ITEM);
        activityMain2Binding.setDetails(clickedItem);

        activityMain2Binding.ratingBar.setRating(Float.parseFloat(Double.toString(clickedItem.getVoteAverage())));
    }
}
