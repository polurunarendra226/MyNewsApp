package com.mynews.module

import android.content.Context
import androidx.room.Room
import com.mynews.Db.NewsDatabase
import com.mynews.Utils.Utils
import com.mynews.api.Api_Service
import com.mynews.models.Article
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  @Provides
  @Singleton
    fun retrofitinstance() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Utils.Base_Url)
        .build()
        .create(Api_Service::class.java)

    @Provides
    @Singleton
    fun CreateDatabase(@ApplicationContext context: Context)=
        Room.databaseBuilder(context,NewsDatabase::class.java,"News_Db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(db:NewsDatabase)=db.getOgjectsOfDao()

}