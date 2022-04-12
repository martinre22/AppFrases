package com.example.mvvm01.domain

import com.example.mvvm01.data.QuoteRepository
import com.example.mvvm01.data.model.QuoteModel
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository :QuoteRepository) {


    suspend operator fun invoke(): List<QuoteModel>?{
        return repository.getAllQuotes()
    }
}

