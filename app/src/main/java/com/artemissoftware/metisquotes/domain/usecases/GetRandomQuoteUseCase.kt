package com.artemissoftware.metisquotes.domain.usecases

import com.artemissoftware.metisquotes.domain.repository.QuoteRepository
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
    suspend operator fun invoke() = quoteRepository.getRandomQuote()
}