package com.artemissoftware.metisquotes.presentation.randomquotes

import com.artemissoftware.metisquotes.domain.models.Quote
import com.artemissoftware.metisquotes.presentation.util.ErrorData

data class RandomQuoteState(
    val isLoading: Boolean = false,
    val quote: Quote? = null,
    val error: ErrorData? = null
)
