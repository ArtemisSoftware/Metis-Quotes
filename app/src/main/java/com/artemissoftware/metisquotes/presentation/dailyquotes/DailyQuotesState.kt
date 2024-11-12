package com.artemissoftware.metisquotes.presentation.dailyquotes

import com.artemissoftware.metisquotes.domain.models.Quote
import com.artemissoftware.metisquotes.presentation.util.ErrorData

data class DailyQuotesState(
    val isLoading: Boolean = false,
    val quotes: List<Quote> = emptyList(),
    val error: ErrorData? = null
)
