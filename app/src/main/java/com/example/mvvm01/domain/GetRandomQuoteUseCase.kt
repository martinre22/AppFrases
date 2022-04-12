package com.example.mvvm01.domain

import com.example.mvvm01.data.QuoteRepository
import com.example.mvvm01.data.model.QuoteModel
import com.example.mvvm01.data.model.QuoteProvider
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val quotesProvider : QuoteProvider
) {

    operator fun invoke(): QuoteModel?{
        val quotes = quotesProvider.quotes
        if (!quotes.isNullOrEmpty()){
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }

        return null
    }
}