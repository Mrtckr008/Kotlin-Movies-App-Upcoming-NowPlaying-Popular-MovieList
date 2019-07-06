package com.example.nytimesmoviesreview.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetTrendMoviesAndSeries {
    @SerializedName("page")
    @Expose
     var page: Int? = null
    @SerializedName("results")
    @Expose
     var results: List<TrendMoviesAndSeriesResult>? = null
    @SerializedName("total_pages")
    @Expose
     var totalPages: Int? = null
    @SerializedName("total_results")
    @Expose
     var totalResults: Int? = null

}

class TrendMoviesAndSeriesResult {

    @SerializedName("original_name")
    @Expose
    var originalName: String? = null
    @SerializedName("id")
    @Expose
    var TrendMoviesAndSeriesid: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null
    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String? = null
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null
    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>? = null
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("origin_country")
    @Expose
    var originCountry: List<String>? = null
    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("video")
    @Expose
    var video: Boolean? = null

}