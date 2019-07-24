package com.example.nytimesmoviesreview.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class GetMovieDetail {

    @SerializedName("adult")
    @Expose
    private var adult: Boolean? = null
    @SerializedName("backdrop_path")
    @Expose
    private var backdropPath: String? = null
    @SerializedName("belongs_to_collection")
    @Expose
    private var belongsToCollection: Any? = null
    @SerializedName("budget")
    @Expose
    private var budget: Int? = null
    @SerializedName("genres")
    @Expose
    private var genres: List<Genre>? = null
    @SerializedName("homepage")
    @Expose
    private var homepage: String? = null
    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("imdb_id")
    @Expose
    private var imdbId: String? = null
    @SerializedName("original_language")
    @Expose
    private var originalLanguage: String? = null
    @SerializedName("original_title")
    @Expose
    private var originalTitle: String? = null
    @SerializedName("overview")
    @Expose
    private var overview: String? = null
    @SerializedName("popularity")
    @Expose
    private var popularity: Double? = null
    @SerializedName("poster_path")
    @Expose
    private var posterPath: String? = null
    @SerializedName("production_companies")
    @Expose
    private var productionCompanies: List<ProductionCompany>? = null
    @SerializedName("production_countries")
    @Expose
    private var productionCountries: List<ProductionCountry>? = null
    @SerializedName("release_date")
    @Expose
    private var releaseDate: String? = null
    @SerializedName("revenue")
    @Expose
    private var revenue: Int? = null
    @SerializedName("runtime")
    @Expose
    private var runtime: Int? = null
    @SerializedName("spoken_languages")
    @Expose
    private var spokenLanguages: List<SpokenLanguage>? = null
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("tagline")
    @Expose
    private var tagline: String? = null
    @SerializedName("title")
    @Expose
    private var title: String? = null
    @SerializedName("video")
    @Expose
    private var video: Boolean? = null
    @SerializedName("vote_average")
    @Expose
    private var voteAverage: Double? = null
    @SerializedName("vote_count")
    @Expose
    private var voteCount: Int? = null

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