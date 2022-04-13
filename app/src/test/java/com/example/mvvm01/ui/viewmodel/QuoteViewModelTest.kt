package com.example.mvvm01.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mvvm01.domain.GetQuotesUseCase
import com.example.mvvm01.domain.GetRandomQuoteUseCase
import com.example.mvvm01.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class QuoteViewModelTest{

    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase
    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    private lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase, getRandomQuoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)

    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all quotes and set the first value`() = runTest {
        val quotesList = listOf(Quote("Argentina que lindo pais", "el paisano"),
            Quote("me encanta programar en Android-kotlin", "yo, desarrollador"))

        coEvery { getQuotesUseCase() } returns quotesList
        quoteViewModel.onCreate()

        assert(quoteViewModel.quoteModel.value == quotesList.first())
    }

    @Test
    fun `when randomQuoteUseCase return a quote set on the livedata`() = runTest {
        val quote = Quote("Soy un JR, con muchas ganas de aprender y aportar", "MartinRe-DevAndroid")
        coEvery { getRandomQuoteUseCase() } returns quote

        quoteViewModel.randomQuote()

        assert(quoteViewModel.quoteModel.value == quote)
    }

    @Test
    fun `if randomQuoteUseCase return null keep the last value`() = runTest {
        val quote = Quote("Soy un JR, con muchas ganas de aprender y aportar", "MartinRe-DevAndroid")
        quoteViewModel.quoteModel.value = quote
        coEvery { getRandomQuoteUseCase() } returns null
        quoteViewModel.randomQuote()

        assert(quoteViewModel.quoteModel.value == quote)
    }
}