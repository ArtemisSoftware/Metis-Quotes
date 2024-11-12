package com.artemissoftware.metisquotes.data.remote.source

import com.artemissoftware.metisquotes.data.remote.HandleApi
import com.artemissoftware.metisquotes.data.remote.api.QuoteApi
import com.artemissoftware.metisquotes.data.remote.dto.DailyQuotesDto
import com.artemissoftware.metisquotes.data.remote.dto.QuoteDto
import javax.inject.Inject

class QuoteApiSource @Inject constructor(
    private val quoteApi: QuoteApi
) {
    suspend fun getRandomQuote(): QuoteDto {
        return HandleApi.safeApiCall { quoteApi.getRandomQuote() }
    }

    suspend fun getDailyQuotes(): DailyQuotesDto {
        return HandleApi.safeApiCall { quoteApi.getDailyQuotes() }
    }
}