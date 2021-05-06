package com.dicoding.movies.ListMovies

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.movies.Data.Movies
import com.dicoding.movies.Data.source.MoviesRepository
import com.dicoding.movies.Utils.DataDummy

class MoviesViewModel(private val moviesRepository : MoviesRepository) : ViewModel() {

    fun getMovies(): LiveData<List<Movies>> = moviesRepository.getAllMovies()

}