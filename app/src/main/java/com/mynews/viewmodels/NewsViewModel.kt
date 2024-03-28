package com.mynews.viewmodels

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mynews.models.Article
import com.mynews.models.News_response
import com.mynews.repositories.News_Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: News_Repository) :ViewModel(){

    val list = repository.getNews().cachedIn(viewModelScope)

    fun list(searchtext:String)=repository.searchnews(searchtext).cachedIn(viewModelScope)



    fun upsert(item:Article)=viewModelScope.launch {
        repository.upsert(item)
    }

    var getNewsItems=repository.getAllNewsItems().cachedIn(viewModelScope)

    fun delete(item: Article)=viewModelScope.launch {
        repository.delete(item)
    }






}