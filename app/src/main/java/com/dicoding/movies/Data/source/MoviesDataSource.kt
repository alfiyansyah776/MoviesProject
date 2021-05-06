package com.dicoding.movies.Data.source

import androidx.lifecycle.LiveData
import com.dicoding.movies.Data.Movies
import com.dicoding.movies.Data.TvShows

interface MoviesDataSource {

    fun getAllMovies(): LiveData<List<Movies>>

    fun getAllTvShows(): LiveData<List<TvShows>>

    fun getMoviesWithId(moviesId: Int) : LiveData<Movies>

    fun getTvShowsWithId(tvId: Int) : LiveData<TvShows>


}