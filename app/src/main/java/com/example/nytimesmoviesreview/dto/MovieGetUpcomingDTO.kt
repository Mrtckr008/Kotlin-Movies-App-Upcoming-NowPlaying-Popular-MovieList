package com.example.nytimesmoviesreview.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class MovieGetUpcoming{

    @SerializedName("results")
    @Expose
     var results: List<ResultUpcoming>? = null
    @SerializedName("page")
    @Expose
     var page: Float? = null
    @SerializedName("total_results")
    @Expose
     var totalResults: Float? = null
    @SerializedName("dates")
    @Expose
     var datesUpcoming: DatesUpcoming? = null
    @SerializedName("total_pages")
    @Expose
     var totalPages: Float? = null

}

class DatesUpcoming {

    @SerializedName("maximum")
    @Expose
    var maximum: String? = null
    @SerializedName("minimum")
    @Expose
    var minimum: String? = null

}

class ResultUpcoming {

    @SerializedName("vote_count")
    @Expose
    var voteCount: Float? = null
    @SerializedName("id")
    @Expose
    var id: Float? = null
    @SerializedName("video")
    @Expose
    var video: Boolean? = null
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null
    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Float>? = null
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

}

