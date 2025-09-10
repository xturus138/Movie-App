package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.Result
import com.example.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<Result>>()
    val movies: LiveData<List<Result>> = _movies

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun loadPopularMovies() {
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val data = repository.getPopularMovies()
                _movies.value = data
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
                _movies.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
