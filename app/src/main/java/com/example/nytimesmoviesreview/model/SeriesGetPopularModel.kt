package com.example.nytimesmoviesreview.model

import com.example.nytimesmoviesreview.dto.Result
import com.example.nytimesmoviesreview.dto.ResultPopularMovies
import com.example.nytimesmoviesreview.dto.ResultPopularSeries

class SeriesGetPopularModel {

    companion object{
        var response2: ArrayList<ResultPopularSeries>? =null

        fun getResponse(): ArrayList<ResultPopularSeries>? {
            return response2
        }

        fun setResponse(response3: ArrayList<ResultPopularSeries>) {
            SeriesGetPopularModel.response2 = response3
        }
    }
}