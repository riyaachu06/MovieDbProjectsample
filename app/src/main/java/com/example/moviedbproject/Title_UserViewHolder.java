package com.example.moviedbproject;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedbproject.databinding.TitleLayoutBinding;
import com.example.moviedbproject.interfaces.OnItemClickListener;
import com.example.moviedbproject.modelclass.Result;

public class Title_UserViewHolder extends RecyclerView.ViewHolder {

    private TitleLayoutBinding titlebinding;
    private OnItemClickListener mListener;

    public Title_UserViewHolder(TitleLayoutBinding titlebinding, OnItemClickListener listener) {
        super(titlebinding.getRoot());
        this.titlebinding = titlebinding;
        mListener=listener;


        titlebinding.cardview.setOnClickListener(new View.OnClickListener() { // we can handle the click as like we do in normal
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    int position = getAdapterPosition(); // Get the index of the view holder
                    if (position != RecyclerView.NO_POSITION) { // Makes sure this position is still valid
                        mListener.onItemClick(position); // we catch the click on the item view then pass it over the interface and then to our activity
                    }
                }

            }
        });

    }

    public TitleLayoutBinding getTitleBinding() {

        return titlebinding;
    }

    public void titlebind(Result titleresult) {
        titlebinding.setTitle(titleresult);
        titlebinding.executePendingBindings();//is important in order to execute the data binding immediately. Otherwise it can populate incorrect view.
    }
}
