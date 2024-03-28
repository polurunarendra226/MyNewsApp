package com.mynews.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mynews.models.Article
import com.mynews.models.News_response

@Database(entities = [Article::class], version = 1)
@TypeConverters(converters::class)
abstract class NewsDatabase:RoomDatabase() {

    abstract fun getOgjectsOfDao():NewsDao

    companion object{
        @Volatile
        private var instance:NewsDatabase?=null

        private val Lock=Any()

        operator fun invoke(context: Context)= instance?: synchronized(Lock){
            instance?: CreateDatabase(context).also { instance = it }
        }


        fun CreateDatabase(context: Context)=Room.databaseBuilder(
            context.applicationContext,NewsDatabase::class.java,"NewsDb").build()
    }
}