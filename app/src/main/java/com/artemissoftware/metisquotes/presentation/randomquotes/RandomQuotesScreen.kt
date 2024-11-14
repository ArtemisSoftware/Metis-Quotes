package com.artemissoftware.metisquotes.presentation.randomquotes

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.metisquotes.domain.models.Quote
import com.artemissoftware.metisquotes.presentation.composables.card.QuoteCard
import com.artemissoftware.metisquotes.presentation.composables.scaffold.MQScaffold
import com.artemissoftware.metisquotes.presentation.util.extensions.pagerHingeTransition
import com.artemissoftware.metisquotes.ui.theme.MetisQuotesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds


@Composable
fun RandomQuoteScreen(
    viewModel: RandomQuoteViewModel = hiltViewModel()
) {
    RandomQuoteContent(
        state = viewModel.state.collectAsState().value,
        event = viewModel::onTriggerEvent
    )
}

@Composable
private fun RandomQuoteContent(
    state: RandomQuoteState,
    event: (RandomQuoteEvent) -> Unit,
) {

    MQScaffold(
        isLoading = state.isLoading,
        error = state.error,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(28.dp)
            ) {
                state.quote?.let { quote ->
                    QuoteCard(
                        quote = quote,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        icon = {
                            Button(
                                onClick = {
                                    event.invoke(RandomQuoteEvent.GetNewQuote)
                                },
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(56.dp),
                                shape = CircleShape,
                                border = BorderStroke(1.dp, Color.White),
                                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                            ) {
                                Icon(Icons.Default.Refresh, contentDescription = "")
                            }
                        }
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun RandomQuoteContentPreview() {
    MetisQuotesTheme {
        RandomQuoteContent(
            state = RandomQuoteState(quote = Quote(content = "I am your quote", author = "Quote master")),
            event = {}
        )
    }
}
