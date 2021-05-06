package com.dicoding.movies.ListMovies

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.movies.Data.Movies
import com.dicoding.movies.Detail.DetailActivity
import com.dicoding.movies.R
import com.dicoding.movies.databinding.ItemMoviesBinding

class  MoviesAdapter(private val callback: MoviesFragmentCallback) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private var listMovies = ArrayList<Movies>()

    fun setMovies(movies: List<Movies>?){
            if (movies == null) return
            this.listMovies.clear()
            this.listMovies.addAll(movies)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MoviesViewHolder {
        val moviesBinding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(moviesBinding)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MoviesViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MoviesViewHolder(private val binding: ItemMoviesBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(movies: Movies){
            with(binding) {
                tvItemTitle.text = movies.title
                scoreDummy.text = itemView.resources.getString(R.string.score)
                tvItemScore.text = movies.score.toString()
                tvItemAired.text = movies.aired
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIES, movies.moviesId)
                    itemView.context.startActivity(intent)
                }
                imgShare.setOnClickListener{callback.onShareClick(movies)}
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/" + movies.poster)
                    .into(imgPoster)
            }
        }
    }

}