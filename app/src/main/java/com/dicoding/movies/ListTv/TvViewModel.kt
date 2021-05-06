package com.dicoding.movies.ListTv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.movies.Data.Movies
import com.dicoding.movies.Data.TvShows
import com.dicoding.movies.Data.source.MoviesRepository
import com.dicoding.movies.Utils.DataDummy

class TvViewModel (private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getTvShows(): LiveData<List<TvShows>> = moviesRepository.getAllTvShows()
}