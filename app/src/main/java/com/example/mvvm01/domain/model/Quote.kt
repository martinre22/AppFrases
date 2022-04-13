package com.example.mvvm01.domain.model

import com.example.mvvm01.data.database.entities.QuoteEntity
import com.example.mvvm01.data.model.QuoteModel

data class Quote(
    val quote:String,
    val author: String
)

fun QuoteModel.toDomain() = Quote(quote, author)
fun QuoteEntity.toDomain() = Quote(quote, author)