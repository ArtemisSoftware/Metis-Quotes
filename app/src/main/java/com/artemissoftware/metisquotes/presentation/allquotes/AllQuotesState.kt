package com.artemissoftware.metisquotes.presentation.allquotes

import androidx.paging.PagingData
import com.artemissoftware.metisquotes.domain.models.Quote
import kotlinx.coroutines.flow.Flow

data class AllQuotesState(
    val isLoading: Boolean = false,
    val quotes: Flow<PagingData<Quote>>? = null,
)