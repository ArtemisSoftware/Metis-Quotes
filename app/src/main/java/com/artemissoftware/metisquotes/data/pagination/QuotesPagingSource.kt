package com.artemissoftware.metisquotes.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.artemissoftware.metisquotes.data.remote.HandleNetwork
import com.artemissoftware.metisquotes.data.remote.dto.QuoteDto
import com.artemissoftware.metisquotes.data.remote.source.QuoteApiSource
import com.artemissoftware.metisquotes.domain.Resource
import com.artemissoftware.metisquotes.domain.error.DataError
import com.artemissoftware.metisquotes.domain.exceptions.PaginationException

class QuotesPagingSource(
    private val quoteApiSource: QuoteApiSource,
) : PagingSource<Int, QuoteDto>() {

    companion object {
        private const val STARTING_KEY = 1
    }

    override fun getRefreshKey(state: PagingState<Int, QuoteDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QuoteDto> {

        val startKey = params.key ?: STARTING_KEY

        val result = HandleNetwork.safeNetworkCall {
            quoteApiSource.getQuotes(page = startKey, limit = params.loadSize)
        }

        return when (result) {
            is Resource.Failure -> {
                LoadResult.Error(throwable = PaginationException(result.error as DataError.NetworkError))
            }
            is Resource.Success -> {
                val quotes = quoteApiSource.getQuotes(page = startKey, limit = params.loadSize)
                val prevKey = if (startKey == STARTING_KEY) null else startKey - 1
                val nextKey = if (quotes.results.isEmpty()) null else (quotes.page + 1)

                LoadResult.Page(data = quotes.results, prevKey = prevKey, nextKey = nextKey)
//                val pokemonList = result.data
//                totalCount += pokemonList.results.size
//
//                var data = emptyList<PokedexEntryDto>()
//                var prevKey: Int? = null
//                var nextKey: Int? = null
//
//                if (totalCount <= pokemonList.count) {
//                    val endOfPaginationReached =  currentPage * PokeApi.PAGE_SIZE >= pokemonList.count
//                    data = pokemonList.results
//                    prevKey = if (currentPage == 0) null else currentPage - 1
//                    nextKey = if (endOfPaginationReached) null else currentPage + 1
//                }
//
//                LoadResult.Page(
//                    data = data,
//                    prevKey = prevKey,
//                    nextKey = nextKey,
//                )
            }
        }
    }
}