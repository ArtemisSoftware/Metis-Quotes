package com.artemissoftware.metisquotes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.artemissoftware.metisquotes.data.mappers.toQuotes
import com.artemissoftware.metisquotes.data.mappers.toQuote
import com.artemissoftware.metisquotes.data.pagination.QuotesPagingSource
import com.artemissoftware.metisquotes.data.remote.HandleNetwork
import com.artemissoftware.metisquotes.data.remote.dto.QuoteDto
import com.artemissoftware.metisquotes.data.remote.dto.QuotesDto
import com.artemissoftware.metisquotes.data.remote.source.QuoteApiSource
import com.artemissoftware.metisquotes.domain.Resource
import com.artemissoftware.metisquotes.domain.models.Quotes
import com.artemissoftware.metisquotes.domain.models.Quote
import com.artemissoftware.metisquotes.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quoteApiSource: QuoteApiSource
): QuoteRepository {

    override suspend fun getRandomQuote(): Resource<Quote> {
        return HandleNetwork.safeNetworkCall {
            quoteApiSource.getRandomQuote().toQuote()
        }
    }

    override suspend fun getDailyQuotes(): Resource<Quotes> {
        return HandleNetwork.safeNetworkCall {
            quoteApiSource.getDailyQuotes().toQuotes()
        }
    }

    override fun getQuotes(): Flow<PagingData<Quote>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { QuotesPagingSource(quoteApiSource) }
        ).flow
            .map { value: PagingData<QuoteDto> ->
                value.map { it.toQuote() }
            }
    }
}