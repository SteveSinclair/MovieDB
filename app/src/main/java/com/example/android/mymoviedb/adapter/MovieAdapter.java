package com.example.android.mymoviedb.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

    private Context mContext;
    private List<Movie> mMovieList;
    private final MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler{
        void OnClick( Movie movie);
    }



    public MovieAdapter(Context context, List<Movie> movieList, MovieAdapterOnClickHandler clickHandler) {
        this.mContext = context;
        this.mMovieList = movieList;
        this.mClickHandler = clickHandler;
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        ImageView imageViewPoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Movie movie = mMovieList.get(adapterPosition);
            mClickHandler.OnClick(movie);
        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.movie_list_item, null);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {


        Movie movie = mMovieList.get(i);
        String path = buildImageUrl(movie.getPosterPath()).toString();

        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder
                .downloader(new OkHttp3Downloader(mContext));
        builder
                .build()
                .load(path)
                .placeholder((R.drawable.ic_launcher_foreground))
                .error(R.drawable.ic_launcher_background)
                .into(movieViewHolder.imageViewPoster);

    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

}
