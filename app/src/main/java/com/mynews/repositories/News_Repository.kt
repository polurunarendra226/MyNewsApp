package com.mynews.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.mynews.Db.NewsDatabase
import com.mynews.api.Api_Service
import com.mynews.models.Article
import com.mynews.paging.NewsPaging
import com.mynews.paging.NewsSearchPaging
import dagger.Provides
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

import javax.inject.Inject

class News_Repository  @Inject constructor(private val apiService: Api_Service,val database: NewsDatabase) {


    fun getNews()= Pager(
        config = PagingConfig(pageSize = 20, maxSize = 200),
        pagingSourceFactory = {NewsPaging(apiService)}
    ).liveData

    fun searchnews(searchtext:String)=Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { NewsSearchPaging(apiService,searchtext) }
    ).flow

    suspend fun upsert(item:Article)= database.getOgjectsOfDao().upsert(item)

    fun getAllNewsItems() = Pager(PagingConfig(pageSize = 20,
        enablePlaceholders = true, maxSize = 200)){
        database.getOgjectsOfDao().getAllNewsItems()
    }.liveData

    suspend fun delete(item: Article)=database.getOgjectsOfDao().delete(item)







}