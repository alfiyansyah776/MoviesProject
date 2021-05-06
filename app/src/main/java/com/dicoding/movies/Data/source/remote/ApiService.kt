package com.dicoding.movies.Data.source.remote
import com.dicoding.movies.Data.source.remote.response.MoviesResponse
import com.dicoding.movies.Data.source.remote.response.ResultsMovies
import com.dicoding.movies.Data.source.remote.response.TvShowsResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("3/movie/now_playing")
    fun getMovies (
        @Query("api_key") apiKey : String
    ): Call<MoviesResponse>


    @GET("3/tv/airing_today")
    fun getTvShows (
        @Query("api_key") apiKey: String
    ): Call<TvShowsResponse>
}