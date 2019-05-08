package com.example.android.mymoviedb.network;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {
    final static String imageSize = "w185";
    final static String basePath = "https://image.tmdb.org/t/p/";

    public static URL buildImageUrl( String posterPath) {
        Uri builtUri = Uri.parse(basePath)
                .buildUpon()
                .appendPath(imageSize)
                .appendEncodedPath(posterPath)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;

    }
}
