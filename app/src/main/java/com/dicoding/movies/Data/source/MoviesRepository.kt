package com.dicoding.movies.Data.source

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.movies.Data.Movies
import com.dicoding.movies.Data.TvShows
import com.dicoding.movies.Data.source.remote.RemoteDataSource
import com.dicoding.movies.Data.source.remote.response.ResultsMovies
import com.dicoding.movies.Data.source.remote.response.ResultsTvShows
import com.dicoding.movies.Data.source.remote.response.TvShowsResponse

class MoviesRepository private constructor(private val remoteDataSource: RemoteDataSource) : MoviesDataSource{

    companion object {
        @Volatile
        private var instance : MoviesRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MoviesRepository =
                instance?: synchronized(this) {
                    instance?: MoviesRepository(remoteDataSource).apply {
                        instance = this
                    } }
                }

    override fun getAllMovies(): LiveData<List<Movies>> {
        val moviesResult = MutableLiveData<List<Movies>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback{
            override fun onAllMoviesReceived(moviesResponse: List<ResultsMovies>) {
                val moviesList = ArrayList<Movies>()
                for (response in moviesResponse){
                    val movies = Movies(
                            response.id,
                            response.posterPath,
                            response.originalLanguage,
                            response.overview,
                            response.title,
                            response.releaseDate,
                            response.voteAverage)

                    moviesList.add(movies)
                }

                moviesResult.postValue(moviesList)
            }

        })


        return moviesResult
    }

    override fun getAllTvShows(): LiveData<List<TvShows>> {
        val tvResult = MutableLiveData<List<TvShows>>()
        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowCallback{
            override fun onAllTvShowReceived(tvShowsResponse: List<ResultsTvShows>) {
                val tvShowList = ArrayList<TvShows>()
                for (response in tvShowsResponse){
                    val tv = TvShows (
                            response.id,
                            response.posterPath,
                            response.originalLanguage,
                            response.overview,
                            response.originalName,
                            response.firstAirDate,
                            response.voteAverage)

                    tvShowList.add(tv)
                }
                if (tvShowList != null){
                    Log.i(TAG, "tvSHow ada")
                }
                tvResult.postValue(tvShowList)
            }

        })

        return tvResult
    }

    override fun getMoviesWithId(moviesId: Int): LiveData<Movies> {
        val movieWithIdResult = MutableLiveData<Movies>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback{
            override fun onAllMoviesReceived(moviesResponse: List<ResultsMovies>) {
                lateinit var movie: Movies
                for (response in moviesResponse){
                    if (response.id == moviesId){
                        movie = Movies(
                                response.id,
                                response.posterPath,
                                response.originalLanguage,
                                response.overview,
                                response.title,
                                response.releaseDate,
                                response.voteAverage)
                    }
                }

                movieWithIdResult.postValue(movie)
            }

        })

        return movieWithIdResult
    }

    override fun getTvShowsWithId(tvId: Int): LiveData<TvShows> {
        val tvWithResult = MutableLiveData<TvShows>()
        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowCallback{
            override fun onAllTvShowReceived(tvShowsResponse: List<ResultsTvShows>) {
                lateinit var tvShows: TvShows
                for (response in tvShowsResponse){
                    if (response.id == tvId){
                        tvShows = TvShows(
                                response.id,
                                response.posterPath,
                                response.originalLanguage,
                                response.overview,
                                response.originalName,
                                response.firstAirDate,
                                response.voteAverage)
                    }
                }
                tvWithResult.postValue(tvShows)
            }
        })

        return tvWithResult
    }


}