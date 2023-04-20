package com.example.myapplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.models.Result
import com.example.myapplication.retrofit.QuotesApi

class QuotePagingSource(val quotesApi: QuotesApi) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {

        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }

            /*if (state.anchorPosition != null){
                val anchorPage = state.closestPageToPosition(state.anchorPosition!!)
                if(anchorPage?.prevKey != null){
                    if (anchorPage != null) {
                        return  anchorPage.prevKey!!.plus(1)
                    }
                    else if(anchorPage?.nextKey!= null){
                        return anchorPage?.nextKey!!.minus(1)
                    }
                    else{
                        return null
                    }
                }
            }*/

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
         return try{
            val position = params.key ?: 1
            val response = quotesApi.getQuotes(position)

            return LoadResult.Page(
                data = response.results,
                prevKey = if(position == 1) null else position -1,
                nextKey = if(position == response.totalPages) null else position +1
            )

        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}