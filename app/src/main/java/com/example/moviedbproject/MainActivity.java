package com.example.moviedbproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.example.moviedbproject.databinding.ActivityMainBinding;
import com.example.moviedbproject.interfaces.OnItemClickListener;
import com.example.moviedbproject.modelclass.DatumResponse;
import com.example.moviedbproject.modelclass.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ActivityMainBinding activityMainBinding;
    String api_key = "8a940d7709a57a2398b0f39f63ce3f30";
    RecyclerView recyclerView;
    Apiinterface apiService;
    List<Result> dataset = new ArrayList<>();
    Title_Adapter title_adapter;
    int page = 1;
    public static final String ITEM = "item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initviews();
        title_adapter.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra(ITEM, dataset.get(position));
                startActivity(intent);
            }
        });
    }

    private void initviews() {
        recyclerView = activityMainBinding.recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        title_adapter = new Title_Adapter(getApplicationContext(), dataset);
        title_adapter.setOnItemClickListener(MainActivity.this);
        recyclerView.setAdapter(title_adapter);
        loadJSON(page);
    }

    private void loadJSON(final int page) {
        apiService = APIclient.getClient().create(Apiinterface.class);
        Call<DatumResponse> call = apiService.getMovieDetails(api_key, page);
        call.enqueue(new Callback<DatumResponse>() {
            @Override
            public void onResponse(Call<DatumResponse> call, Response<DatumResponse> response) {
                if (response != null) {

                    DatumResponse item = response.body();

                    if (item.getResults() != null) {
                        dataset.addAll(item.getResults());
                        title_adapter.notifyItemRangeChanged(page, dataset.size());
                    }

                }
            }

            @Override
            public void onFailure(Call<DatumResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    public void drawnext() {
        ++page;
        loadJSON(page);
    }


}
