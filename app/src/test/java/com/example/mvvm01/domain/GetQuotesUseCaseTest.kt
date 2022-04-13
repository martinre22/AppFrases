package com.example.mvvm01.domain

import com.example.mvvm01.data.QuoteRepository
import com.example.mvvm01.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuotesUseCaseTest{

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    private lateinit var getQuotesUseCase: GetQuotesUseCase
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(quoteRepository)

    }

    @Test
    fun `when api doesnt return anything then get values from database`() = runBlocking{
        //given
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()

        //when
        getQuotesUseCase()

        //then
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //given
        val myList = listOf(Quote("solo se que no se nada", "socrates"))
        coEvery { quoteRepository.getAllQuotesFromApi() } returns myList

        //when
        val response = getQuotesUseCase()

        //then
        coVerify(exactly = 1) { quoteRepository.clearQuotes()}
        coVerify(exactly = 1){ quoteRepository.insertQuotes(any()) }
        coVerify(exactly = 0) { quoteRepository.getAllQuotesFromDatabase() }
        assert(myList == response)
    }
}