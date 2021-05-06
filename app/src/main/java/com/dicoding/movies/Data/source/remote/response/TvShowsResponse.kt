package com.dicoding.movies.Data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class TvShowsResponse(

	@field:SerializedName("results")
	val results: List<ResultsTvShows>

)

@Parcelize
data class ResultsTvShows(

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("original_name")
	val originalName: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("vote_average")
	val voteAverage : Double
):Parcelable
