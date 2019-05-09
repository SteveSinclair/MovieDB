package com.example.android.mymoviedb.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.mymoviedb.R;
import com.example.android.mymoviedb.model.Movie;

public class MovieDetails extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        Bitmap bitmapPoster = getIntent().getParcelableExtra("bitmapPoster");

        ImageView imageViewPoster = findViewById(R.id.imageViewPoster);
        TextView textViewVoteAverage = findViewById(R.id.textViewVoteAverage);
        TextView textViewRating = findViewById(R.id.textViewRating);
        TextView textViewSynopsis = findViewById(R.id.textViewSynopsis);
        TextView textViewYear = findViewById(R.id.textViewYear);

        setTitle(movie.getTitle());
        textViewVoteAverage.setText(movie.getVoteAverage().toString());
        textViewRating.setText(movie.getPopularity().toString());
        textViewSynopsis.setText(movie.getOverview());
        textViewYear.setText(movie.getReleaseDate());


        imageViewPoster.setImageBitmap(bitmapPoster);
    }
}
