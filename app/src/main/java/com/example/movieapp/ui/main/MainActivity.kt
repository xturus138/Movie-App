package com.example.movieapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieViewModel by viewModels()
    private val adapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovies.layoutManager = GridLayoutManager(this, 2)
        binding.rvMovies.adapter = adapter

        viewModel.isLoading.observe(this) { loading ->
            binding.tvStatus.text = if (loading) "Loading..." else ""
        }
        viewModel.error.observe(this) { err ->
            binding.tvStatus.text = err ?: binding.tvStatus.text
        }
        viewModel.movies.observe(this) { list ->
            adapter.submitList(list)
            if (list.isNotEmpty()) binding.tvStatus.text = ""
            if (list.isEmpty() && (viewModel.error.value == null)) {
                binding.tvStatus.text = "No Data!"
            }
        }
        viewModel.loadPopularMovies()
    }
}
