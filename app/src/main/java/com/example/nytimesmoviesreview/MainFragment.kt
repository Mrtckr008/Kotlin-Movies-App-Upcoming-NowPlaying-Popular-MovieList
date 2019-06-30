package com.example.nytimesmoviesreview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nytimesmoviesreview.adapter.MoviesAdapter
import com.example.nytimesmoviesreview.model.MovieNowPlayingModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =inflater.inflate(R.layout.main_fragment,container,false)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuRecyclerView.apply {
            layoutManager=LinearLayoutManager(activity)

            adapter=MoviesAdapter(MovieNowPlayingModel.getResponse()!!.toList())
        }
    }
companion object{
    fun newInstance():MainFragment= MainFragment()

}

}


