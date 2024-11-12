package com.artemissoftware.metisquotes.data.remote.api

import com.artemissoftware.metisquotes.data.remote.dto.DailyQuotes
import com.artemissoftware.metisquotes.data.remote.dto.QuoteDto
import retrofit2.http.GET

interface QuoteApi {

    @GET("random?maxLength=150&minLength=100")
    suspend fun getRandomQuote(): QuoteDto

    @GET("quotes?limit=20")
    suspend fun getDailyQuotes(): DailyQuotes

    companion object {
        const val BASE_URL = "https://api.quotable.io/"
    }
}