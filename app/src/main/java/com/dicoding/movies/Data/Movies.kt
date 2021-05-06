package com.dicoding.movies.Data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
        var moviesId: Int,
        var poster: String,
        var language: String,
        var synopsis: String,
        var title: String,
        var aired: String,
        var score: Double
        ):Parcelable