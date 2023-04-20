package com.example.myapplication.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.myapplication.paging.QuotePagingSource
import com.example.myapplication.retrofit.QuotesApi
import javax.inject.Inject

class QuoteRespository @Inject constructor(val quotesApi: QuotesApi) {

    fun getQuotes() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {QuotePagingSource(quotesApi)}
    ).liveData

}