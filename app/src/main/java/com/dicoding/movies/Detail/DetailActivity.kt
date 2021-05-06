package com.dicoding.movies.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.movies.Data.Movies
import com.dicoding.movies.Data.TvShows
import com.dicoding.movies.Data.source.MoviesRepository
import com.dicoding.movies.Utils.DataDummy
import com.dicoding.movies.databinding.ActivityDetailBinding
import com.dicoding.movies.databinding.ContentDetailBinding
import com.dicoding.movies.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_MOVIES = "extra_movies"
    }

    private lateinit var detailBinding: ContentDetailBinding
    private lateinit var moviesRepository : MoviesRepository
    private lateinit var tvShowRepository: MoviesRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activitydetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailBinding = activitydetailBinding.detailContent

        setContentView(activitydetailBinding.root)

        setSupportActionBar(activitydetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if(extras != null) {
            val moviesId = extras.getInt(EXTRA_MOVIES, 0)
            val tvId = extras.getInt(EXTRA_TV, 0)

            if (moviesId != null) {
                activitydetailBinding.progressBar.visibility = View.VISIBLE
                activitydetailBinding.content.visibility = View.INVISIBLE

                viewModel.setSelectedMovies(moviesId)
                viewModel.getMovies().observe(this, {movies ->
                    activitydetailBinding.progressBar.visibility = View.GONE
                    activitydetailBinding.progressBar.visibility = View.VISIBLE

                    populateMovies(movies)

                })

            }else if (tvId != null) {
                activitydetailBinding.progressBar.visibility = View.VISIBLE
                activitydetailBinding.content.visibility = View.INVISIBLE
                viewModel.setSelectedTvShows(tvId)
                viewModel.getTvShows().observe(this, {tvShows ->
                    activitydetailBinding.progressBar.visibility = View.GONE
                    activitydetailBinding.progressBar.visibility = View.VISIBLE

                    populateTvShows(tvShows)

                })
            }
        }
    }

    private fun populateMovies(movies: Movies){
        detailBinding.textTitle.text = movies.title
        detailBinding.scorevalue.text = movies.score.toString()
        detailBinding.textSynopsis.text = movies.synopsis
        detailBinding.episodevalue.text = movies.language
        detailBinding.statusvalue.text = movies.aired



        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/"+movies.poster)
                .into(detailBinding.imageView)

    }

    private fun populateTvShows(tvShows: TvShows){
        detailBinding.textTitle.text = tvShows.title
        detailBinding.scorevalue.text = tvShows.score.toString()
        detailBinding.textSynopsis.text = tvShows.synopsis
        detailBinding.episodevalue.text = tvShows.language
        detailBinding.statusvalue.text = tvShows.aired



        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/" + tvShows.poster)
                .into(detailBinding.imageView)

    }
}