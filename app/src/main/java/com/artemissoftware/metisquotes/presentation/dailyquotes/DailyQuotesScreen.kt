package com.artemissoftware.metisquotes.presentation.dailyquotes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.metisquotes.R
import com.artemissoftware.metisquotes.domain.models.Quote
import com.artemissoftware.metisquotes.presentation.composables.card.QuoteCard
import com.artemissoftware.metisquotes.presentation.composables.scaffold.MQScaffold
import com.artemissoftware.metisquotes.presentation.util.extensions.pagerHingeTransition
import com.artemissoftware.metisquotes.ui.theme.MetisQuotesTheme

@Composable
fun DailyQuotesScreen(viewModel: DailyQuotesViewModel = hiltViewModel()) {
    DailyQuotesContent(
        state = viewModel.state.collectAsState().value,
        event = viewModel::onTriggerEvent
    )
}

@Composable
private fun DailyQuotesContent(
    state: DailyQuotesState,
    event: (DailyQuotesEvent) -> Unit
) {

    val pagerState = rememberPagerState(pageCount = { state.quotes.size })

    MQScaffold(
        isLoading = state.isLoading,
        error = state.error,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    state = pagerState,
                    beyondViewportPageCount = 2
                ) { page ->
                    QuoteCard(
                        quote = state.quotes[page],
                        modifier = Modifier
                            .fillMaxWidth()
                            .pagerHingeTransition(page, pagerState).clickable {
                                Log.d("QUOTE", "Content: ${state.quotes[page].content}")
                            },
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


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                onClick = { event.invoke(DailyQuotesEvent.GetQuotes) }
                            ),
                        tint = colorResource(id = R.color.white)
                    )
                }

            }
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun DailyQuotesContentPreview() {
    MetisQuotesTheme {
        DailyQuotesContent(
            state = DailyQuotesState(
                quotes = listOf(
                    Quote(
                        author = "The prince",
                        content = "I am the emperor of all I see"
                    )
                )
            ),
            event = {},
        )
    }
}
