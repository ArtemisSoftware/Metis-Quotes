package com.artemissoftware.metisquotes.presentation.navigation

sealed class Route(val route: String) {
    data object DailyQuotes : Route("daily_quotes")
    data object RandomQuote : Route("random_quote")
}