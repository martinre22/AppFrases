package com.example.mvvm01.data.network

import com.example.mvvm01.core.RetrofitHelper
import com.example.mvvm01.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getQuotes():List<QuoteModel>{
        //coroutines
        //devolvemos la llamada en un hilo secundario para no saturar la interfaz del usuario
        // o el hilo principal
        return withContext(Dispatchers.IO){
            val response = retrofit.create(QuoteApiClient::class.java).getAllQuotes()
            response.body() ?: emptyList()
        }

    }
}