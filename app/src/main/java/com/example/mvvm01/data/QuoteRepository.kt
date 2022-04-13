package com.example.mvvm01.data

import com.example.mvvm01.data.database.dao.QuoteDao
import com.example.mvvm01.data.database.entities.QuoteEntity
import com.example.mvvm01.data.model.QuoteModel
import com.example.mvvm01.data.network.QuoteService
import com.example.mvvm01.domain.model.Quote
import com.example.mvvm01.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val dao:QuoteDao
) {

    suspend fun getAllQuotesFromApi() : List<Quote>{
        val response: List<QuoteModel> = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote>{
        val response: List<QuoteEntity> = dao.getAllQuotes()
        val listQuoteEntity: List<Quote> = response.map { it.toDomain() }
        return listQuoteEntity
    }

    suspend fun insertQuotes(quotes:List<QuoteEntity>){
        dao.insertAllQuotes(quotes)
    }

    suspend fun clearQuotes() {
        dao.deleteAllQuotes()
    }
}