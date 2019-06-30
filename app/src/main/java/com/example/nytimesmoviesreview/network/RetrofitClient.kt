package com.example.nytimesmoviesreview.network

import com.example.nytimesmoviesreview.NytimesServiceInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        fun getClient():Retrofit{
            return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()}
    }



}