package com.talent.sweetp.repository

import com.talent.sweetp.api.QuoteApiService
import com.talent.sweetp.model.Quote
import retrofit2.Response

class QuoteRepository(private val apiService: QuoteApiService) {
    suspend fun getRandomQuote(): Response<Quote> {
        return apiService.getRandomQuote()
    }
}
