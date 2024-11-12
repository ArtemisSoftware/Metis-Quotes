package com.artemissoftware.metisquotes.presentation.dailyquotes

sealed class DailyQuotesEvent {
    data object GetQuotes: DailyQuotesEvent()
}
