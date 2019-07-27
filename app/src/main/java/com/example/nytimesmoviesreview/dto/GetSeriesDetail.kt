package com.example.nytimesmoviesreview.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.net.Network





class GetSeriesDetail {
    @SerializedName("backdrop_path")
    @Expose
     val backdropPath: String? = null
    @SerializedName("created_by")
    @Expose
     val createdBy: List<CreatedBy>? = null
    @SerializedName("episode_run_time")
    @Expose
     val episodeRunTime: List<Int>? = null
    @SerializedName("first_air_date")
    @Expose
     val firstAirDate: String? = null
    @SerializedName("genres")
    @Expose
     val genres: List<SeriesDetailGenre>? = null
    @SerializedName("homepage")
    @Expose
     val homepage: String? = null
    @SerializedName("id")
    @Expose
     val id: Int? = null
    @SerializedName("in_production")
    @Expose
     val inProduction: Boolean? = null
    @SerializedName("languages")
    @Expose
     val languages: List<String>? = null
    @SerializedName("last_air_date")
    @Expose
     val lastAirDate: String? = null
    @SerializedName("last_episode_to_air")
    @Expose
     val lastEpisodeToAir: LastEpisodeToAir? = null
    @SerializedName("name")
    @Expose
     val name: String? = null
    @SerializedName("next_episode_to_air")
    @Expose
     val nextEpisodeToAir: NextEpisodeToAir? = null
    @SerializedName("networks")
    @Expose
     val networks: List<Network>? = null
    @SerializedName("number_of_episodes")
    @Expose
     val numberOfEpisodes: Int? = null
    @SerializedName("number_of_seasons")
    @Expose
     val numberOfSeasons: Int? = null
    @SerializedName("origin_country")
    @Expose
     val originCountry: List<String>? = null
    @SerializedName("original_language")
    @Expose
     val originalLanguage: String? = null
    @SerializedName("original_name")
    @Expose
     val originalName: String? = null
    @SerializedName("overview")
    @Expose
     val overview: String? = null
    @SerializedName("popularity")
    @Expose
     val popularity: Double? = null
    @SerializedName("poster_path")
    @Expose
     val posterPath: String? = null
    @SerializedName("production_companies")
    @Expose
     val productionCompanies: List<ProductionCompanySeriesDetail>? = null
    @SerializedName("seasons")
    @Expose
     val seasons: List<Season>? = null
    @SerializedName("status")
    @Expose
     val status: String? = null
    @SerializedName("type")
    @Expose
     val type: String? = null
    @SerializedName("vote_average")
    @Expose
     val voteAverage: Float? = null
    @SerializedName("vote_count")
    @Expose
     val voteCount: Int? = null
}


class CreatedBy {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("credit_id")
    @Expose
    var creditId: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("gender")
    @Expose
    var gender: Int? = null
    @SerializedName("profile_path")
    @Expose
    var profilePath: String? = null
}

class SeriesDetailGenre {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
}

class LastEpisodeToAir {
    @SerializedName("air_date")
    @Expose
    var airDate: String? = null
    @SerializedName("episode_number")
    @Expose
    var episodeNumber: Int? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("production_code")
    @Expose
    var productionCode: String? = null
    @SerializedName("season_number")
    @Expose
    var seasonNumber: Int? = null
    @SerializedName("show_id")
    @Expose
    var showId: Int? = null
    @SerializedName("still_path")
    @Expose
    var stillPath: String? = null
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float? = null
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
}

class Network {
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("logo_path")
    @Expose
    var logoPath: String? = null
    @SerializedName("origin_country")
    @Expose
    var originCountry: String? = null
}

class NextEpisodeToAir {
    @SerializedName("air_date")
    @Expose
    var airDate: String? = null
    @SerializedName("episode_number")
    @Expose
    var episodeNumber: Int? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("production_code")
    @Expose
    var productionCode: String? = null
    @SerializedName("season_number")
    @Expose
    var seasonNumber: Int? = null
    @SerializedName("show_id")
    @Expose
    var showId: Int? = null
    @SerializedName("still_path")
    @Expose
    var stillPath: Any? = null
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Int? = null
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null

}


class ProductionCompanySeriesDetail {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("logo_path")
    @Expose
    var logoPath: Any? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("origin_country")
    @Expose
    var originCountry: String? = null
}

class Season {
    @SerializedName("air_date")
    @Expose
    var airDate: String? = null
    @SerializedName("episode_count")
    @Expose
    var episodeCount: Int? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("poster_path")
    @Expose
    var posterPath: Any? = null
    @SerializedName("season_number")
    @Expose
    var seasonNumber: Int? = null
}