package com.artemissoftware.metisquotes.data.mappers

import com.artemissoftware.metisquotes.data.remote.dto.QuotesDto
import com.artemissoftware.metisquotes.data.remote.dto.QuoteDto
import com.artemissoftware.metisquotes.domain.models.Quotes
import com.artemissoftware.metisquotes.domain.models.Quote

internal fun QuoteDto.toQuote(): Quote {
    return Quote(
        content = content,
        author = author
    )
}

internal fun QuotesDto.toDailyQuotes(): Quotes {
    return Quotes(quotes = this.quotes.map { it.toQuote() })
}