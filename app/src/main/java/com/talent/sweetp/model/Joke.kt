package com.talent.sweetp.model

data class Joke(
    val error: Boolean,
    val category: String,
    val type: String,
    val joke: String,
    val flags: Flags,
    val id: Int,
    val safe: Boolean,
    val lang: String
)
