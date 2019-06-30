package com.example.nytimesmoviesreview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nytimesmoviesreview.adapter.MoviesAdapter
import com.example.nytimesmoviesreview.adapter.MoviesAdapterGetPopular
import com.example.nytimesmoviesreview.dto.Result
import com.example.nytimesmoviesreview.model.MovieGetPopularModel
import com.example.nytimesmoviesreview.model.MovieNowPlayingModel
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment :Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =inflater.inflate(R.layout.detail_fragment,container,false)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailFragmentsRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                adapter = MoviesAdapterGetPopular(MovieGetPopularModel.getResponse()!!.toList())
            }

    }
    companion object{
        fun newInstance():DetailFragment= DetailFragment()

    }

}