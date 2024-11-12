package com.artemissoftware.metisquotes.data.mappers

import com.artemissoftware.metisquotes.data.remote.dto.DailyQuotesDto
import com.artemissoftware.metisquotes.data.remote.dto.QuoteDto
import com.artemissoftware.metisquotes.domain.models.DailyQuotes
import com.artemissoftware.metisquotes.domain.models.Quote

fun QuoteDto.toQuote(): Quote {
    return Quote(
        content = content,
        author = author
    )
}

fun DailyQuotesDto.toDailyQuotes(): DailyQuotes {
    return DailyQuotes(quotes = this.quotes.map { it.toQuote() })
}