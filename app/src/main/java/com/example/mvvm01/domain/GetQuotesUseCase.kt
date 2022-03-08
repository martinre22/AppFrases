package com.example.mvvm01.domain

import com.example.mvvm01.data.QuoteRepository
import com.example.mvvm01.data.model.QuoteModel


//tambien llamados interactors
//no es necesario poner UseCase al final
//solo deben realizar una accion
class GetQuotesUseCase {
    private val repository = QuoteRepository()

    suspend operator fun invoke(): List<QuoteModel>?{
        return repository.getAllQuotes()
    }
}

//suspend operator fun invoke()
//al crear  e instanciar la clase llama directamente al metodo sin invocarlo
/*antes
private val getQuoteUseCase = GetQuotesUseCase
llamamos al metodo
getQuoteUseCase.kljljkjkl()

**Ahora
solo instanciamos el objeto e invoca al metodo automaticamente
getQuoteUseCase()
 */