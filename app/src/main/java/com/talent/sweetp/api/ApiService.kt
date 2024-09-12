package com.talent.sweetp.api

import com.talent.sweetp.model.Joke
import com.talent.sweetp.model.Quote
import com.talent.sweetp.model.QuoteList
import com.talent.sweetp.model.TriviaResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("random")
    suspend fun getRandomQuote(): Response<Quote>

    @GET("quotes")
    suspend fun getQuotesByPage(@Query("page") page: Int): Response<QuoteList>

    @GET("quotes/{id}")
    suspend fun getQuoteById(@Path("id") id: String): Response<Quote>

    @GET("joke/Any?blacklistFlags=explicit&type=single&amount=1")
    suspend fun getRandomJoke(): Response<Joke>

    @GET("api.php?amount=20&category=23&difficulty=easy&type=multiple")
    suspend fun getTriviaQuestions(): Response<TriviaResponse>

    companion object {
        private const val BASE_URL_QUOTES = "https://api.quotable.io/"
        private const val BASE_URL_JOKES = "https://v2.jokeapi.dev/"
        private const val BASE_URL_TRIVIA = "https://opentdb.com/"

        private fun getUnsafeRetrofit(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .client(getUnsafeOkHttpClient())  // Use unsafe OkHttpClient
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun createQuoteApi(): ApiService {
            return getUnsafeRetrofit(BASE_URL_QUOTES).create(ApiService::class.java)
        }

        fun createJokeApi(): ApiService {
            return getUnsafeRetrofit(BASE_URL_JOKES).create(ApiService::class.java)
        }

        fun createTriviaApi(): ApiService {
            return getUnsafeRetrofit(BASE_URL_TRIVIA).create(ApiService::class.java)
        }
    }
}
