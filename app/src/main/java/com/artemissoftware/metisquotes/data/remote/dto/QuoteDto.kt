package com.artemissoftware.metisquotes.data.remote.dto

import com.squareup.moshi.Json

data class QuoteDto(
    @field:Json(name = "author")
    val author: String,
    @field:Json(name = "authorSlug")
    val authorSlug: String,
    @field:Json(name = "content")
    val content: String,
    @field:Json(name = "dateAdded")
    val dateAdded: String,
    @field:Json(name = "dateModified")
    val dateModified: String,
    @field:Json(name = "_id")
    val id: String,
    @field:Json(name = "length")
    val length: String = "",
    @field:Json(name = "tags")
    val tags: List<String>,
)
