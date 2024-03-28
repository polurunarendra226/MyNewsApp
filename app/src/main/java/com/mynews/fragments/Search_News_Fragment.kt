package com.mynews.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toolbar
import androidx.core.widget.addTextChangedListener

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.search.SearchView


import com.mynews.R
import com.mynews.adapters.News_Adapter
import com.mynews.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Search_News_Fragment : Fragment(R.layout.fragment_search__news_) {
lateinit var et_text: EditText
lateinit var rv_search_breaking_news:RecyclerView
lateinit var adapter:News_Adapter
val viewmodel:NewsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = News_Adapter()
        et_text = view.findViewById(R.id.search_view)
        rv_search_breaking_news =view.findViewById(R.id.rv_search_news)
        rv_search_breaking_news.adapter=adapter
        rv_search_breaking_news.layoutManager=LinearLayoutManager(activity)

        lifecycleScope.launch {
            viewmodel.list(et_text.text.trim().toString()).collectLatest {
                adapter.submitData(it)
            }
        }
        adapter.setOnItemClickListner {
            findNavController().navigate(Search_News_FragmentDirections.actionSearchNewsFragmentToArticleNewsFragment(it))
        }

        et_text.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lifecycleScope.launch {
                    viewmodel.list(et_text.text.trim().toString()).collectLatest {
                        adapter.submitData(it)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        })


    }
}