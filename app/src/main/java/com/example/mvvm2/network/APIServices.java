package com.example.mvvm2.network;

import com.example.mvvm2.model.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServices {

    @GET("photos")
    Call<List<MovieModel>> getMovieList();
}
