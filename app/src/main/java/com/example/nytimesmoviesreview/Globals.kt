package com.example.nytimesmoviesreview

import com.example.nytimesmoviesreview.dto.Result


class Globals {

    companion object forSendData{
        var responseGetApi: ArrayList<Result>? =null
        fun returnData():ArrayList<Result>?{
            return responseGetApi
        }
    }
}