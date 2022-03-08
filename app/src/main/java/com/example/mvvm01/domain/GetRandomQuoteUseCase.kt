package com.example.mvvm01.domain

import com.example.mvvm01.data.QuoteRepository
import com.example.mvvm01.data.model.QuoteModel
import com.example.mvvm01.data.model.QuoteProvider

class GetRandomQuoteUseCase {
    private val repository = QuoteRepository()

    operator fun invoke(): QuoteModel?{
        val quotes = QuoteProvider.quotes
        if (!quotes.isNullOrEmpty()){
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }

        return null
    }
}