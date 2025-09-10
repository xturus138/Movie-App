package com.example.movieapp.data.repository

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.model.Result
import com.example.movieapp.data.remote.api.MovieApi
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieApi
) {
    suspend fun getPopularMovies(): List<Result> {
        val response = api.getPopularMovies(BuildConfig.TMDB_API_KEY)
        return response.results
    }
}