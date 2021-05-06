package com.dicoding.movies.Data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import com.dicoding.movies.Data.source.remote.response.MoviesResponse
import com.dicoding.movies.Data.source.remote.response.ResultsMovies
import com.dicoding.movies.Data.source.remote.response.ResultsTvShows
import com.dicoding.movies.Data.source.remote.response.TvShowsResponse
import com.dicoding.movies.Utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
                instance?: synchronized(this){
                    instance?: RemoteDataSource(helper).apply {
                        instance = this
                    }
                }
    }

    fun getAllMovies(callback: LoadMoviesCallback){
        callback.onAllMoviesReceived(jsonHelper.loadMovies())
    }

    fun getAllTvShows(callback: LoadTvShowCallback) {
        callback.onAllTvShowReceived(jsonHelper.loadTvShows())
    }

    interface LoadMoviesCallback{
        fun onAllMoviesReceived(moviesResponse: List<ResultsMovies>)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowsResponse: List<ResultsTvShows>)
    }
}