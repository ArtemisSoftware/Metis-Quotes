package com.artemissoftware.metisquotes.data.remote.dto

import com.squareup.moshi.Json

data class DailyQuotes(
    @field:Json(name = "count")
    val count: Int= 0,
    @field:Json(name = "lastItemIndex")
    val lastItemIndex: Int= 0,
    @field:Json(name = "page")
    val page: Int= 0,
    @field:Json(name = "results")
    val quotes: List<QuoteDto>,
    @field:Json(name = "totalCount")
    val totalCount: Int= 0,
    @field:Json(name = "totalPages")
    val totalPages: Int= 0,
)
