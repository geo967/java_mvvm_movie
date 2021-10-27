package com.example.mvvm2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvm2.adapter.MovieListAdapter;
import com.example.mvvm2.model.MovieModel;
import com.example.mvvm2.viewmodel.MovieListViewModel;

import java.util.List;



public class MainActivity extends AppCompatActivity implements MovieListAdapter.ItemClickListener {

    private List<MovieModel> movieModelList;
    private MovieListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        HttpsTrustManager.allowAllSSL();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TextView noResult = findViewById(R.id.noResult);

        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieListAdapter(this, movieModelList, this);
        recyclerView.setAdapter(adapter);

        MovieListViewModel viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewModel.getMoviesListObserver().observe(this, movieModels -> {
            if (movieModels != null) {
                movieModelList = movieModels;
                adapter.setMovieList(movieModels);
                noResult.setVisibility(View.GONE);

            } else {
                noResult.setVisibility(View.VISIBLE);
            }
        });
        viewModel.makeApiCall();
    }
    @Override
    public void onMovieClick(MovieModel movie){
        Toast.makeText(this,"clciked movie name is:"+movie.getTitle(),Toast.LENGTH_SHORT).show();
    }

}