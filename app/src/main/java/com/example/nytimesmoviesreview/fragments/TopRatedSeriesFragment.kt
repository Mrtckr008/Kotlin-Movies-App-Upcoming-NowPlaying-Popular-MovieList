package com.example.nytimesmoviesreview.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nytimesmoviesreview.R
import com.example.nytimesmoviesreview.adapter.SeriesAdapterGetTopRatedSeries
import com.example.nytimesmoviesreview.model.SeriesGetTopRatedModel
import kotlinx.android.synthetic.main.now_playing_fragment.*

class TopRatedSeriesFragment :Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =inflater.inflate(
        R.layout.now_playing_fragment,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailFragmentsRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                adapter = SeriesAdapterGetTopRatedSeries(SeriesGetTopRatedModel.getResponse()!!.toList(),context)
            }

    }
    companion object{
        fun newInstance(): TopRatedSeriesFragment = TopRatedSeriesFragment()

    }

}