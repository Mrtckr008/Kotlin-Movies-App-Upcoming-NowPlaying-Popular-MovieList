package com.example.nytimesmoviesreview.model

import com.example.nytimesmoviesreview.dto.Result
import com.example.nytimesmoviesreview.dto.ResultPopularMovies
import com.example.nytimesmoviesreview.dto.ResultUpcoming

class MovieGetUpcomingModel {

    companion object{
        var response2: ArrayList<ResultUpcoming>? =null

        fun getResponse(): ArrayList<ResultUpcoming>? {
            return response2
        }

        fun setResponse(response3: ArrayList<ResultUpcoming>) {
            MovieGetUpcomingModel.response2 = response3
        }
    }
}