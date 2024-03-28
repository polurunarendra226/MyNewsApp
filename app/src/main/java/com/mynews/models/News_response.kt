package com.mynews.models


data class News_response(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)