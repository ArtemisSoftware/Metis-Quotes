package com.artemissoftware.metisquotes.presentation.composables.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.artemissoftware.metisquotes.R
import com.artemissoftware.metisquotes.domain.models.Quote
import com.artemissoftware.metisquotes.ui.theme.MetisQuotesTheme

@Composable
fun QuoteCard(
    quote: Quote,
    icon: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().height(380.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
               modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .alpha(0.6f),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(rememberRandomSampleImageUrl(width = 1200))
                        .build(),
                    contentDescription = "",
                )

                QuoteDetail(
                    quote = quote,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp)
                        .padding(
                            top = 64.dp, bottom = 24.dp
                        )
                )
            }
            icon()
        }
    }

}

fun randomSampleImageUrl(
    seed: Int = rangeForRandom.random(),
    width: Int = 300,
    height: Int = width,
): String {
    return "https://picsum.photos/seed/$seed/$width/$height"
}

private val rangeForRandom = (0..100000)
@Composable
fun rememberRandomSampleImageUrl(
    seed: Int = rangeForRandom.random(),
    width: Int = 300,
    height: Int = width,
): String = remember { randomSampleImageUrl(seed, width, height) }

@Composable
private fun QuoteDetail (
    quote: Quote,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = quote.content.uppercase(),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(0.0f, 0.0f),
                    blurRadius = 10f
                )
            ),
            color = Color.White
        )

        Spacer(
            modifier = Modifier
                .height(40.dp)
        )

        Text(
            text = quote.author,
            style = MaterialTheme.typography.bodyLarge.copy(
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(0.0f, 0.0f),
                    blurRadius = 25f
                )
            ),
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun QuoteCardPreview() {
    MetisQuotesTheme {
        QuoteCard(
            Quote(
                author = "The prince",
                content = "I am the emperor of all I see"
            ),
            modifier = Modifier.fillMaxWidth(),
            icon = {
                Box(modifier = Modifier.background(Color.Red).size(30.dp))
            },
        )
    }
}