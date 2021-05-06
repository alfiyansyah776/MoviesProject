package com.dicoding.movies.di

import android.content.Context
import com.dicoding.movies.Data.source.MoviesRepository
import com.dicoding.movies.Data.source.remote.RemoteDataSource
import com.dicoding.movies.Utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MoviesRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return MoviesRepository.getInstance(remoteDataSource)
    }
}