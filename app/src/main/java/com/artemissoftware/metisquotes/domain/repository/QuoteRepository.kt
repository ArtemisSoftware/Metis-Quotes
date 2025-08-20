package com.artemissoftware.metisquotes.domain.repository

import com.artemissoftware.metisquotes.domain.Resource
import com.artemissoftware.metisquotes.domain.models.Quotes
import com.artemissoftware.metisquotes.domain.models.Quote

interface QuoteRepository {

    suspend fun getRandomQuote(): Resource<Quote>
    suspend fun getDailyQuotes(): Resource<Quotes>
}