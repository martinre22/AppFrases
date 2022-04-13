package com.example.mvvm01.domain

import com.example.mvvm01.data.QuoteRepository
import com.example.mvvm01.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {

    suspend operator fun invoke(): Quote?{
        val quotes = quoteRepository.getAllQuotesFromDatabase()
        if (!quotes.isNullOrEmpty()){
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }

        return null
    }
}