package com.talent.sweetp.repository

import com.talent.sweetp.api.ApiService
import com.talent.sweetp.model.Joke
import com.talent.sweetp.model.Quote
import com.talent.sweetp.model.QuoteList
import com.talent.sweetp.model.TriviaResponse
import com.talent.sweetp.model.WeatherResponse
import retrofit2.Response

class Repository(
    private val apiService: ApiService,
    private val jokeApiService: ApiService,
    private val triviaApiService: ApiService,
    private val weatherApiService: ApiService
) {
    suspend fun getRandomQuote(): Response<Quote> {
        return apiService.getRandomQuote()
    }

    suspend fun getQuotesByPage(page: Int): Response<QuoteList> {
        return apiService.getQuotesByPage(page)
    }

    suspend fun getQuoteById(id: String): Response<Quote> {
        return apiService.getQuoteById(id)
    }

    suspend fun getRandomJoke(): Response<Joke> {
        return jokeApiService.getRandomJoke()
    }

    suspend fun getTriviaQuestions(): Response<TriviaResponse> {
        return triviaApiService.getTriviaQuestions()
    }

    suspend fun getWeatherByCity(city: String, apiKey: String): WeatherResponse {
        return apiService.getWeatherByCity(city, apiKey)
    }
}
