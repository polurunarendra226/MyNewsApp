package com.mynews.Db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mynews.models.Article
import com.mynews.models.News_response


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item:Article):Long



    @Query("SELECT * FROM News_Db ORDER BY id DESC")
    fun getAllNewsItems(): PagingSource<Int,Article>

    @Delete
    suspend fun delete(item: Article)
//    @Delete
//    suspend fun deleteArticleATPosition(item: Int)
}