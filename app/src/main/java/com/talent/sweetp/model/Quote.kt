package com.talent.sweetp.model

data class Quote(
    val _id: String,
    val content: String,
    val author: String,
    val tags: List<String>,
    val authorSlug: String,
    val length: Int,
    val dateAdded: String,
    val dateModified: String
)

