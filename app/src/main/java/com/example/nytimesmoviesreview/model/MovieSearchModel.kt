package com.example.nytimesmoviesreview.model

import com.example.nytimesmoviesreview.dto.ResultPopularSeries
import com.example.nytimesmoviesreview.dto.SearchMovieResults

class MovieSearchModel {

    companion object{
        var response2: ArrayList<SearchMovieResults>? =null

        fun getResponse(): ArrayList<SearchMovieResults>? {
            return response2
        }

        fun setResponse(response3: ArrayList<SearchMovieResults>) {
            MovieSearchModel.response2 = response3
        }

        var queriesData = HashMap<String,String>()
    }


}