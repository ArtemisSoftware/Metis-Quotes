package com.artemissoftware.metisquotes.presentation.allquotes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.artemissoftware.metisquotes.presentation.composables.card.QuoteCard
import com.artemissoftware.metisquotes.presentation.util.extensions.pagerHingeTransition
import com.artemissoftware.metisquotes.ui.theme.MetisQuotesTheme

@Composable
fun AllQuotesScreen(
    viewModel: AllQuotesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AllQuotesContent(
        state = state,
        onEvent = viewModel::onTriggerEvent
    )
}

@Composable
private fun AllQuotesContent(
    state: AllQuotesState,
    onEvent: (AllQuotesEvent) -> Unit,
) {

    val lazyListState = rememberLazyListState()



    state.quotes?.let {

        val item = it.collectAsLazyPagingItems()

        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxWidth()
        ) {

            items(
                count = item.itemCount,
                key = item.itemKey { it.content }
            ) { index ->
                item[index]?.let { quote ->
                    QuoteCard(
                        quote = quote,
                        modifier = Modifier
                            .fillMaxWidth(),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.FormatQuote,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .background(Color.Transparent)
                            )
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AllQuotesContentPreview() {
    MetisQuotesTheme {
        AllQuotesContent(
            state = AllQuotesState(),
            onEvent = {}
        )
    }
}