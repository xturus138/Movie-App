// ui/main/MovieAdapter.kt
package com.example.movieapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.model.Result
import com.example.movieapp.utils.Constants

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.VH>() {

    private val items = ArrayList<Result>()

    fun submitList(newItems: List<Result>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class VH(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    ) {
        val poster: ImageView = itemView.findViewById(R.id.ivPoster)
        val title: TextView = itemView.findViewById(R.id.tvTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(parent)

    override fun onBindViewHolder(holder: VH, position: Int) {
        val movie = items[position]
        val posterUrl = movie.poster_path?.let { Constants.IMAGE_BASE_URL + it }
        Glide.with(holder.itemView)
            .load(posterUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.poster)

        holder.title.text = movie.title
    }

    override fun getItemCount() = items.size
}
