package com.example.nytimesmoviesreview.model

import com.example.nytimesmoviesreview.dto.Result

class MovieNowPlayingModel {

    companion object{
        var response2: ArrayList<Result>? =null

        fun getResponse(): ArrayList<Result>? {
            return response2
        }

        fun setResponse(response3: ArrayList<Result>) {
            MovieNowPlayingModel.response2 = response3
        }
    }
}