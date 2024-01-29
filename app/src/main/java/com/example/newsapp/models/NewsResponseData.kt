package com.example.newsapp.models

data class NewsResponseData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)