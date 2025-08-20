package com.artemissoftware.metisquotes.data.remote.api

import com.artemissoftware.metisquotes.data.remote.dto.QuotesDto
import com.artemissoftware.metisquotes.data.remote.dto.QuoteDto
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {

    @GET("random")
    suspend fun getRandomQuote(@Query("maxLength") maxLength: Int = 150, @Query("minLength") minLength: Int = 100): QuoteDto

    @GET("quotes")
    suspend fun getDailyQuotes(@Query("limit") limit: Int = 20): QuotesDto

    @GET("quotes")
    suspend fun getQuotes(@Query("page") page: Int = 1, @Query("limit") limit: Int = 5): QuotesDto

    companion object {
        const val BASE_URL = "http://api.quotable.io/"
    }
}