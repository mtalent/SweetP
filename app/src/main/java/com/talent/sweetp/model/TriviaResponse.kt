package com.talent.sweetp.model

data class TriviaResponse(
    val response_code: Int,
    val results: List<TriviaQuestion>
)
