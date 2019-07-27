package com.example.nytimesmoviesreview.model

import com.example.nytimesmoviesreview.dto.GetMovieDetail
import com.example.nytimesmoviesreview.dto.Result
import com.example.nytimesmoviesreview.dto.ResultPopularMovies

class MovieGetDetailModel {

    companion object{
        var response2: GetMovieDetail? =null

        fun getResponse(): GetMovieDetail? {
            return response2
        }

        fun setResponse(response3: GetMovieDetail) {
            MovieGetDetailModel.response2 = response3
        }
    }
}