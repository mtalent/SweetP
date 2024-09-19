package com.talent.sweetp.model

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val name: String
)
