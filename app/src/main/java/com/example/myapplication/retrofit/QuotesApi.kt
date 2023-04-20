package com.example.myapplication.retrofit

import com.example.myapplication.models.QuoteList
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {
    @GET("/quotes")
    suspend fun getQuotes(@Query("page")page :Int ):QuoteList

}