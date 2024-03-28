package com.mynews.api

import androidx.lifecycle.MutableLiveData
import com.mynews.Utils.Utils
import com.mynews.models.Article
import com.mynews.models.News_response
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api_Service {

    @GET("/v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        country:String,
        @Query("page")
        page:Int = 1,
        @Query("apiKey")
        apiKey:String=Utils.Api_Key
    ):News_response

    @GET("/v2/everything")
    suspend fun getpagingsearchnews(
        @Query("q")
        searchquery:String,
        @Query("page")
        pageNumber:Int =1,
        @Query("apiKey")
        apiKey:String= Utils.Api_Key
    ):News_response

}