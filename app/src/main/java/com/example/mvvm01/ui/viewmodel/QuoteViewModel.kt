package com.example.mvvm01.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm01.data.model.QuoteModel
import com.example.mvvm01.data.model.QuoteProvider
import com.example.mvvm01.domain.GetQuotesUseCase
import com.example.mvvm01.domain.GetRandomQuoteUseCase
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {

    val quoteModel = MutableLiveData<QuoteModel>()
    var getQuotesUseCase = GetQuotesUseCase()
    val isLoading = MutableLiveData<Boolean>()

    val getRandomQuoteUseCase = GetRandomQuoteUseCase()

    fun onCreate() {
        //nos permite crear una coroutine que se controla automaticamente
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()
            if (!result.isNullOrEmpty()){
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }
    fun randomQuote(){
        isLoading.postValue(true)
        val quote = getRandomQuoteUseCase()
        if (quote != null)
        {
            quoteModel.postValue(quote!!)
        }
        isLoading.postValue(false)
    }


}