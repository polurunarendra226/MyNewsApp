package com.mynews.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mynews.api.Api_Service
import com.mynews.models.Article

class NewsSearchPaging(var apiService: Api_Service, var query:String): PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try{
            var position = params.key?:1
            var response = apiService.getpagingsearchnews(query,pageNumber = position)
            return LoadResult.Page(
                data = response.articles,
                prevKey = if (position==1)null else position-1,
                nextKey = if (position==response.totalResults)null else position+1
            )

        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}