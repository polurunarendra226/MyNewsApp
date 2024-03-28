package com.mynews.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mynews.MainActivity

import com.mynews.R
import com.mynews.adapters.News_Adapter
import com.mynews.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Breaking_News_Fragment : Fragment(R.layout.fragment_breaking__news_) {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: News_Adapter

    val viemodel:NewsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_breaking_news)
        adapter = News_Adapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        viemodel.list.observe(viewLifecycleOwner,{
            adapter.submitData(lifecycle,it)
        })

        adapter.setOnItemClickListner {
            findNavController().navigate(Breaking_News_FragmentDirections.actionBreakingNewsFragmentToArticleNewsFragment(it))
        }


    }
}

