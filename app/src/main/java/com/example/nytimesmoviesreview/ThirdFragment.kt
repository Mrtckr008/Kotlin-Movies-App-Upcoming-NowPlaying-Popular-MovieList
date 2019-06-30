package com.example.nytimesmoviesreview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nytimesmoviesreview.adapter.MoviesAdapter
import com.example.nytimesmoviesreview.adapter.MoviesAdapterGetUpcoming
import com.example.nytimesmoviesreview.model.MovieGetUpcomingModel
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.third_fragment.*

class ThirdFragment:Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =inflater.inflate(R.layout.third_fragment,container,false)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            thirdFragmentsRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                adapter = MoviesAdapterGetUpcoming(MovieGetUpcomingModel.getResponse()!!.toList())
            }

    }
    companion object{
        fun newInstance():ThirdFragment= ThirdFragment()
    }

}