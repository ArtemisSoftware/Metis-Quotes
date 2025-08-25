package com.artemissoftware.metisquotes.presentation.navigation

import androidx.annotation.StringRes
import com.artemissoftware.metisquotes.R

sealed class Route(val route: String, @StringRes val description: Int) {
    data object DailyQuotes : Route(route = "daily_quotes", description = R.string.daily_quotes)
    data object RandomQuote : Route(route ="random_quote", description = R.string.random_quote)
    data object AllQuotes : Route(route ="all_quotes", description = R.string.random_quote)
}