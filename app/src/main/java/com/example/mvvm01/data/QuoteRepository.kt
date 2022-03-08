package com.example.mvvm01.data

import com.example.mvvm01.data.model.QuoteModel
import com.example.mvvm01.data.model.QuoteProvider
import com.example.mvvm01.data.network.QuoteService

class QuoteRepository {

    private val api = QuoteService()

    suspend fun getAllQuotes() : List<QuoteModel>{
        val response = api.getQuotes()
        QuoteProvider.quotes = response
        return response
    }
}