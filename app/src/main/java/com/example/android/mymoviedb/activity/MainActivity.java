package com.example.android.mymoviedb.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.mymoviedb.network.Api;
import com.example.android.mymoviedb.BuildConfig;
import com.example.android.mymoviedb.model.Movie;
import com.example.android.mymoviedb.adapter.MovieAdapter;
import com.example.android.mymoviedb.model.MoviesResponse;
import com.example.android.mymoviedb.R;
import com.example.android.mymoviedb.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int SORT_BY_POPULARITY = 0;
    private static final int SORT_BY_RATING = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private MovieAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;
    List<Movie> movies;
    Api api = RetrofitClientInstance.getRetrofitInstance().create(Api.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshMovies(SORT_BY_POPULARITY);
    }

    private void refreshMovies(int sortBy) {

        Call<MoviesResponse> call;
        switch (sortBy) {
            case SORT_BY_POPULARITY:
                call = api.getPopularMovies(BuildConfig.MOVIE_DB_API_KEY);
                setTitle(R.string.titlePopular);
                break;
            case SORT_BY_RATING:
                call = api.getTopRatedMovies(BuildConfig.MOVIE_DB_API_KEY);
                setTitle(R.string.titleRating);
                break;
            default:
                call = api.getPopularMovies(BuildConfig.MOVIE_DB_API_KEY);
                setTitle(R.string.titlePopular);
        }


        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage(getString(R.string.loadingMsg));
        progressDialog.show();

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                progressDialog.dismiss();
                MoviesResponse movieResponse = response.body();


                movies = movieResponse.getMovies();
                if (movies != null) {
                    showMovies(movies);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e(TAG, t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showMovies(List<Movie> movies) {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MovieAdapter(this, movies);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itemByPopularity:
                refreshMovies(SORT_BY_POPULARITY);
                return true;
            case R.id.itemByRating:
                refreshMovies(SORT_BY_RATING);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
