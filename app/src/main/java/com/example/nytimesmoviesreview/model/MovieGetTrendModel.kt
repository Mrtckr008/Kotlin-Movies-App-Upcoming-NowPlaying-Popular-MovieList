package com.example.nytimesmoviesreview.model

import com.example.nytimesmoviesreview.dto.TrendMoviesAndSeriesResult

class MovieGetTrendModel {

    companion object{
        var response2: ArrayList<TrendMoviesAndSeriesResult>? =null

        fun getResponse(): ArrayList<TrendMoviesAndSeriesResult>? {
            return response2
        }

        fun setResponse(response3: ArrayList<TrendMoviesAndSeriesResult>) {
            MovieGetTrendModel.response2 = response3
        }
    }


}