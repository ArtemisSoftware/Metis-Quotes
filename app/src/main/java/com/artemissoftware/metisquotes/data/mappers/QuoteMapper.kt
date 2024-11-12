package com.artemissoftware.metisquotes.data.mappers

import com.artemissoftware.metisquotes.data.remote.dto.QuoteDto
import com.artemissoftware.metisquotes.domain.models.Quote

fun QuoteDto.toQuote(): Quote {
    return Quote(
        content = content,
        author = author
    )
}