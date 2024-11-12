package com.artemissoftware.metisquotes.data.repository

import com.artemissoftware.metisquotes.data.mappers.toDailyQuotes
import com.artemissoftware.metisquotes.data.mappers.toQuote
import com.artemissoftware.metisquotes.data.remote.HandleNetwork
import com.artemissoftware.metisquotes.data.remote.source.QuoteApiSource
import com.artemissoftware.metisquotes.domain.Resource
import com.artemissoftware.metisquotes.domain.models.DailyQuotes
import com.artemissoftware.metisquotes.domain.models.Quote
import com.artemissoftware.metisquotes.domain.repository.QuoteRepository
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quoteApiSource: QuoteApiSource
): QuoteRepository {

    override suspend fun getRandomQuote(): Resource<Quote> {
        return HandleNetwork.safeNetworkCall {
            quoteApiSource.getRandomQuote().toQuote()
        }
    }

    override suspend fun getDailyQuotes(): Resource<DailyQuotes> {
        return HandleNetwork.safeNetworkCall {
            quoteApiSource.getDailyQuotes().toDailyQuotes()
        }
    }
}