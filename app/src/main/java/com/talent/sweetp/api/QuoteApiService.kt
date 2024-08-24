package com.talent.sweetp.api

import com.talent.sweetp.model.Quote
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface QuoteApiService {
    @GET("random")
    suspend fun getRandomQuote(): Response<Quote>

    companion object {
        private const val BASE_URL = "https://api.quotable.io/"

        fun create(): QuoteApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(QuoteApiService::class.java)
        }
    }
}
