package com.example.mvvm2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm2.HttpsTrustManager;
import com.example.mvvm2.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvm2.model.MovieModel;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private final Context context;
    private List<MovieModel> movieList;
    private final ItemClickListener clickListener;

    public MovieListAdapter(Context context, List<MovieModel> movieList, ItemClickListener clickListener) {
        this.context = context;
        this.movieList = movieList;
        this.clickListener = clickListener;
    }

    public void setMovieList(List<MovieModel> movieList){
        this.movieList=movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(this.movieList.get(position).getTitle());

        holder.itemView.setOnClickListener(v -> clickListener.onMovieClick(movieList.get(position)));
        HttpsTrustManager.allowAllSSL();
        Glide.with(context)
                .load(this.movieList.get(position).getImage())
                .apply(RequestOptions.centerCropTransform())
                .fitCenter()
                .circleCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(this.movieList!=null){
            return this.movieList.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.titleView);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
    public interface ItemClickListener{
        void onMovieClick(MovieModel movie);
    }
}
