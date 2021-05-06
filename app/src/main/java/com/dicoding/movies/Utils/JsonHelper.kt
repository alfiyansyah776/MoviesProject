package com.dicoding.movies.Utils

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.movies.Data.source.remote.ApiConfig
import com.dicoding.movies.Data.source.remote.response.MoviesResponse
import com.dicoding.movies.Data.source.remote.response.ResultsMovies
import com.dicoding.movies.Data.source.remote.response.ResultsTvShows
import com.dicoding.movies.Data.source.remote.response.TvShowsResponse
import retrofit2.Call
import retrofit2.Response

class JsonHelper (private val context: Context) {
    private val apiKey = "5c377cc06e2fc296835de427123e1c85"


    fun loadMovies(): List<ResultsMovies> {
        //fungsi ini akan mengambil API dari TMDB yang mana akan mengambil movie yang sedang tayang
        var _listMovies : List<ResultsMovies> = ArrayList()
        val client = ApiConfig.getApiService().getMovies(apiKey)
        client.enqueue(object : retrofit2.Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    val movieResult : MoviesResponse? = response.body()
                    if (movieResult != null) {
                        _listMovies = movieResult.results
                    }
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })

        return _listMovies
    }

    fun loadTvShows(): List<ResultsTvShows>{
        //fungsi ini akan mengambil API dari TMDB yang mana akan mengambil tvShow yang sedang tayang
        var listTv : List<ResultsTvShows> = ArrayList()
        val client = ApiConfig.getApiService().getTvShows(apiKey)
        client.enqueue(object : retrofit2.Callback<TvShowsResponse>{
            override fun onResponse(call: Call<TvShowsResponse>, response: Response<TvShowsResponse>) {
                if (response.isSuccessful){
                    listTv = response.body()?.results!!
                }else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })

        return listTv

    }
}