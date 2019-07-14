package com.example.nytimesmoviesreview.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.nytimesmoviesreview.fragments.MostPopularMoviesFragment
import com.example.nytimesmoviesreview.fragments.NowPlayingMoviesFragment

class MyPagerFragmentAdapter(fm:FragmentManager):FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position){
            0->{
                NowPlayingMoviesFragment()
            }
            else ->{
                MostPopularMoviesFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0-> {"Maın Fragment"}
            1-> {"Template Fragment"}
            else -> {return "Detaıl Fragment"}
        }
    }
}