package com.example.nytimesmoviesreview.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nytimesmoviesreview.R
import com.example.nytimesmoviesreview.adapter.MoviesAdapterGetUpcoming
import com.example.nytimesmoviesreview.model.MovieGetTrendModel
import kotlinx.android.synthetic.main.trend_movies_series_fragment.*

class TrendMoviesAndSeriesFragment:Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =inflater.inflate(
        R.layout.trend_movies_series_fragment,container,false)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            thirdFragmentsRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                adapter = MoviesAdapterGetUpcoming(MovieGetTrendModel.getResponse()!!.toList())
            }

    }
    companion object{
        fun newInstance(): TrendMoviesAndSeriesFragment =
            TrendMoviesAndSeriesFragment()
    }

}