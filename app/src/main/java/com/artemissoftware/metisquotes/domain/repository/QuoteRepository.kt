package com.artemissoftware.metisquotes.domain.repository

import com.artemissoftware.metisquotes.domain.Resource
import com.artemissoftware.metisquotes.domain.models.Quote

interface QuoteRepository {

    suspend fun getRandomQuote(): Resource<Quote>
}