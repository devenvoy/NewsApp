package com.example.newsapp.interfaces

import com.example.newsapp.Constants.Companion.API_KEY
import com.example.newsapp.models.NewsResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {

    @GET("v2/top-headlines")
    fun getNewsHeadLines(
        @Query("country") country: String?,
        @Query("category") category: String?,
        @Query("page") page: Int?,
        @Query("apiKey") apikey: String = API_KEY
    ): Call<NewsResponseData>

}