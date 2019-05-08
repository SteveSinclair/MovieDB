package com.example.android.mymoviedb.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.mymoviedb.model.Movie;
import com.example.android.mymoviedb.R;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.android.mymoviedb.network.NetworkUtils.buildImageUrl;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private static final String TAG = MovieAdapter.class.getSimpleName();

    private Context context;
    private List<Movie> movieList;
    private MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler{
        void OnClick( Movie movie);
    }

    public MovieAdapter(Context context, List<Movie> movieList, MovieAdapterOnClickHandler clickHandler) {
        this.context = context;
        this.movieList = movieList;
        this.mClickHandler = clickHandler;
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder implements MovieAdapterOnClickHandler{
        ImageView imageViewPoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            itemView.setOnClickListener((OnClickListener) this);
        }


        @Override
        public void OnClick(Movie movie) {

        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_list_item, null);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {


        Movie movie = movieList.get(i);
        String path = buildImageUrl(movie.getPosterPath()).toString();

        Picasso.Builder builder = new Picasso.Builder(context);
        builder
                .downloader(new OkHttp3Downloader(context));
        builder
                .build()
                .load(path)
                .placeholder((R.drawable.ic_launcher_foreground))
                .error(R.drawable.ic_launcher_background)
                .into(movieViewHolder.imageViewPoster);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

}
