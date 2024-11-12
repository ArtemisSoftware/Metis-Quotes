package com.artemissoftware.metisquotes.data.remote.di

import com.artemissoftware.metisquotes.data.remote.source.QuoteApiSource
import com.artemissoftware.metisquotes.data.repository.QuoteRepositoryImpl
import com.artemissoftware.metisquotes.domain.repository.QuoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideQuoteRepository(quoteApiSource: QuoteApiSource): QuoteRepository {
        return QuoteRepositoryImpl(quoteApiSource = quoteApiSource)
    }
}