package com.dicoding.movies.ListTv

import com.dicoding.movies.Data.TvShows

interface TvFragmentCallback {
    fun onShareClick(tvShows: TvShows)
}