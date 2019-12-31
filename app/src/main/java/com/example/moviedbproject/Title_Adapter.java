package com.example.moviedbproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedbproject.databinding.TitleLayoutBinding;
import com.example.moviedbproject.interfaces.OnItemClickListener;
import com.example.moviedbproject.modelclass.DatumResponse;
import com.example.moviedbproject.modelclass.Result;

import java.util.ArrayList;
import java.util.List;

public class Title_Adapter extends RecyclerView.Adapter {

    private List<Result> dataset=new ArrayList<>();
    Context context;
    private MainActivity mListener;


    public Title_Adapter(Context context, List<Result> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TitleLayoutBinding titlebinding = TitleLayoutBinding
                .inflate(inflater, parent, false);
        return new Title_UserViewHolder(titlebinding,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Title_UserViewHolder title_userViewHolder;

        if (holder instanceof Title_UserViewHolder) {
            title_userViewHolder = (Title_UserViewHolder) holder;
            settitleuserviewholder(title_userViewHolder, position);
        }
        if (dataset.size() > 1) {
            if (position == dataset.size() - 1) {
                mListener.drawnext();
            }
        }
    }

    private void settitleuserviewholder(Title_UserViewHolder title_userViewHolder, int position) {

        Result titlemovie = dataset.get(position);
        String imageicon = titlemovie.getPosterPath();
        String IMAGE_URL = "https://image.tmdb.org/t/p/w200/" + imageicon;
        if (titlemovie != null) {
            title_userViewHolder.titlebind(titlemovie);
            Utility.loadImageUsingGlide(context, title_userViewHolder.getTitleBinding().imageView, IMAGE_URL);
        }


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public void setOnItemClickListener(MainActivity mainActivity) {
        mListener = mainActivity;
    }
}
