package com.example.nytimesmoviesreview.model

import com.example.nytimesmoviesreview.dto.Result
import com.example.nytimesmoviesreview.dto.ResultPopularMovies

class MovieGetPopularModel {

    companion object{
        var response2: ArrayList<ResultPopularMovies>? =null

        fun getResponse(): ArrayList<ResultPopularMovies>? {
            return response2
        }

        fun setResponse(response3: ArrayList<ResultPopularMovies>) {
            MovieGetPopularModel.response2 = response3
        }
    }
}