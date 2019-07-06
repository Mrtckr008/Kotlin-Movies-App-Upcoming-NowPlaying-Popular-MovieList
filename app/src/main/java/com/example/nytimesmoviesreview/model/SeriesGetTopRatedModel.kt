package com.example.nytimesmoviesreview.model

import com.example.nytimesmoviesreview.dto.Result
import com.example.nytimesmoviesreview.dto.ResultGetTopRatedSeries
import com.example.nytimesmoviesreview.dto.ResultPopularMovies
import com.example.nytimesmoviesreview.dto.ResultPopularSeries

class SeriesGetTopRatedModel {

    companion object{
        var response2: ArrayList<ResultGetTopRatedSeries>? =null

        fun getResponse(): ArrayList<ResultGetTopRatedSeries>? {
            return response2
        }

        fun setResponse(response3: ArrayList<ResultGetTopRatedSeries>) {
            SeriesGetTopRatedModel.response2 = response3
        }
    }
}