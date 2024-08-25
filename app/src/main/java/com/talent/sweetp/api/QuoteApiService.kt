package com.talent.sweetp.api

import com.talent.sweetp.model.Quote
import com.talent.sweetp.model.QuoteList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QuoteApiService {
    @GET("random")
    suspend fun getRandomQuote(): Response<Quote>

    @GET("quotes")
    suspend fun getQuotesByPage(@Query("page") page: Int): Response<QuoteList>

    @GET("quotes/{id}")
    suspend fun getQuoteById(@Path("id") id: String): Response<Quote>

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

