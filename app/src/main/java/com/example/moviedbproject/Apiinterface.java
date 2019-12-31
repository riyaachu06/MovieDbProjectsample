package com.example.moviedbproject;

import com.example.moviedbproject.modelclass.DatumResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Apiinterface {

    @GET("3/movie/now_playing")
    Call<DatumResponse> getMovieDetails(@Query("api_key") String api_key,@Query("page") int page);

}
