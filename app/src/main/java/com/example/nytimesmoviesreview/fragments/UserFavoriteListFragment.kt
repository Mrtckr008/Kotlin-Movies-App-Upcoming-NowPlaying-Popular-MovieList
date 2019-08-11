package com.example.nytimesmoviesreview.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nytimesmoviesreview.R
import com.example.nytimesmoviesreview.adapter.UserListsAdapter
import kotlinx.android.synthetic.main.user_list_fragment.*

class UserFavoriteListFragment :Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =inflater.inflate(
        R.layout.user_list_fragment,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userListRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = UserListsAdapter(context)
        }

    }
    companion object{
        fun newInstance(): UserFavoriteListFragment =
            UserFavoriteListFragment()

    }
}