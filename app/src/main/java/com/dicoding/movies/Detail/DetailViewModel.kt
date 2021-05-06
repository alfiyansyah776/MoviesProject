package com.dicoding.movies.Detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.movies.Data.Movies
import com.dicoding.movies.Data.TvShows
import com.dicoding.movies.Data.source.MoviesRepository
import com.dicoding.movies.Utils.DataDummy

class DetailViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    private var movieId: Int = 0
    private var tvId: Int = 0

    fun setSelectedMovies(movieId : Int){
        this.movieId = movieId
    }

    fun setSelectedTvShows(tvId : Int){
        this.tvId = tvId
    }

    fun getMovies() : LiveData<Movies> = moviesRepository.getMoviesWithId(movieId)

    fun getTvShows() : LiveData<TvShows> = moviesRepository.getTvShowsWithId(tvId)


}