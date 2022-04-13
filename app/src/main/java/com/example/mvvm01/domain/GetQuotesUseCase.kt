package com.example.mvvm01.domain

import com.example.mvvm01.data.QuoteRepository
import com.example.mvvm01.data.database.entities.toDatabase
import com.example.mvvm01.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository :QuoteRepository) {


    suspend operator fun invoke(): List<Quote>?{
        val quotes = repository.getAllQuotesFromApi()

        return if (quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        }else{
            repository.getAllQuotesFromDatabase()
        }
    }
}

