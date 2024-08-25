package com.talent.sweetp.repository

import com.talent.sweetp.api.QuoteApiService
import com.talent.sweetp.model.Quote
import com.talent.sweetp.model.QuoteList
import retrofit2.Response

class QuoteRepository(private val apiService: QuoteApiService) {
    suspend fun getRandomQuote(): Response<Quote> {
        return apiService.getRandomQuote()
    }

    suspend fun getQuotesByPage(page: Int): Response<QuoteList> {
        return apiService.getQuotesByPage(page)
    }

    suspend fun getQuoteById(id: String): Response<Quote> {
        return apiService.getQuoteById(id)
    }
}
