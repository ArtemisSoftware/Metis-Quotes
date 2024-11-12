package com.artemissoftware.metisquotes.data.remote.source

import com.artemissoftware.metisquotes.data.remote.HandleApi
import com.artemissoftware.metisquotes.data.remote.api.QuoteApi
import com.artemissoftware.metisquotes.data.remote.dto.QuoteDto

class QuoteApiSource /*@Inject*/ constructor(
    private val quoteApi: QuoteApi
) {
    suspend fun getRandomQuote(): QuoteDto {
        return HandleApi.safeApiCall { quoteApi.getRandomQuote() }
    }
}