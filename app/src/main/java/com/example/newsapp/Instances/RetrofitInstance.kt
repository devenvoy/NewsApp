package com.example.newsapp.Instances

import com.example.newsapp.Constants.Companion.BASE_URL
import com.example.newsapp.interfaces.NewsInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val services by lazy {
        retrofit.create(NewsInterface::class.java)
    }
}