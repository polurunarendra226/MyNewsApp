package com.mynews.models
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Keep
@Entity(tableName = "News_Db")
data class Article(
    val author: String?=null,
    val content: String?=null,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}