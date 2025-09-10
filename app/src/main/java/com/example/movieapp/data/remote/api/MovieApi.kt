package com.example.movieapp.data.remote.api

import android.telecom.Call
import com.example.movieapp.data.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Movies
}