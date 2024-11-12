package com.artemissoftware.metisquotes.presentation.randomquotes

sealed class RandomQuoteEvent {
    data object GetNewQuote: RandomQuoteEvent()
}
