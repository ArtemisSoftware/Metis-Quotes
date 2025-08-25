package com.artemissoftware.metisquotes.domain.repository

import com.artemissoftware.metisquotes.domain.Resource
import com.artemissoftware.metisquotes.domain.models.Quotes
import com.artemissoftware.metisquotes.domain.models.Quote
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData

interface QuoteRepository {

    suspend fun getRandomQuote(): Resource<Quote>
    suspend fun getDailyQuotes(): Resource<Quotes>
    fun getQuotes(): Flow<PagingData<Quote>>
}