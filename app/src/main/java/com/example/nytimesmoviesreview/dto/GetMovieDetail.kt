package com.example.nytimesmoviesreview.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetMovieDetail {

    @SerializedName("adult")
    @Expose
     var adult: Boolean? = null
    @SerializedName("backdrop_path")
    @Expose
     var backdropPath: String? = null
    @SerializedName("belongs_to_collection")
    @Expose
     var belongsToCollection: Any? = null
    @SerializedName("budget")
    @Expose
     var budget: Int? = null
    @SerializedName("genres")
    @Expose
     var genres: List<Genre>? = null
    @SerializedName("homepage")
    @Expose
     var homepage: String? = null
    @SerializedName("id")
    @Expose
     var id: Int? = null
    @SerializedName("imdb_id")
    @Expose
     var imdbId: String? = null
    @SerializedName("original_language")
    @Expose
     var originalLanguage: String? = null
    @SerializedName("original_title")
    @Expose
     var originalTitle: String? = null
    @SerializedName("overview")
    @Expose
     var overview: String? = null
    @SerializedName("popularity")
    @Expose
     var popularity: Double? = null
    @SerializedName("poster_path")
    @Expose
     var posterPath: String? = null
    @SerializedName("production_companies")
    @Expose
     var productionCompanies: List<ProductionCompany>? = null
    @SerializedName("production_countries")
    @Expose
     var productionCountries: List<ProductionCountry>? = null
    @SerializedName("release_date")
    @Expose
     var releaseDate: String? = null
    @SerializedName("revenue")
    @Expose
     var revenue: Int? = null
    @SerializedName("runtime")
    @Expose
     var runtime: Int? = null
    @SerializedName("spoken_languages")
    @Expose
     var spokenLanguages: List<SpokenLanguage>? = null
    @SerializedName("status")
    @Expose
     var status: String? = null
    @SerializedName("tagline")
    @Expose
     var tagline: String? = null
    @SerializedName("title")
    @Expose
     var title: String? = null
    @SerializedName("video")
    @Expose
     var video: Boolean? = null
    @SerializedName("vote_average")
    @Expose
     var voteAverage: Double? = null
    @SerializedName("vote_count")
    @Expose
     var voteCount: Int? = null

}

class Genre {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}

class ProductionCompany {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("logo_path")
    @Expose
    var logoPath: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("origin_country")
    @Expose
    var originCountry: String? = null

}

class ProductionCountry {

    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}

class SpokenLanguage {

    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}