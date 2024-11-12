package com.artemissoftware.metisquotes.presentation.composables.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.artemissoftware.metisquotes.R
import com.artemissoftware.metisquotes.presentation.composables.loading.LoadingProgress
import com.artemissoftware.metisquotes.presentation.composables.text.UiText
import com.artemissoftware.metisquotes.presentation.dailyquotes.DailyQuotesEvent
import com.artemissoftware.metisquotes.presentation.util.ErrorData
import kotlin.math.roundToInt

@Composable
fun MQScaffold(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    error: ErrorData? = null,
    bottomBar: @Composable() ((Modifier) -> Unit?)? = null,
) {
    val bottomBarHeight = 124.0.dp
    val bottomBarHeightPx = with(LocalDensity.current) { bottomBarHeight.roundToPx().toFloat() }
    val bottomBarOffsetHeightPx = remember { mutableFloatStateOf(0f) }

// connection to the nested scroll system and listen to the scroll
// happening inside child LazyColumn
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.floatValue + delta
                bottomBarOffsetHeightPx.floatValue = newOffset.coerceIn(-bottomBarHeightPx, 0f)

                return Offset.Zero
            }
        }
    }

    val collapsedModifier = Modifier
        .height(bottomBarHeight)
        .offset { IntOffset(x = 0, y = -bottomBarOffsetHeightPx.floatValue.roundToInt()) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Scaffold(
            modifier = Modifier
                .then(modifier)
                .nestedScroll(nestedScrollConnection),
            bottomBar = {
                bottomBar?.invoke(collapsedModifier)
            },
            content = {  innerPadding ->

                val topPadding =  0.dp

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = topPadding)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        content.invoke()
                    }
                }
            },
        )

        LoadingProgress(isLoading = isLoading)

        error?.let { ErrorPlaceHolder(error = it) }
    }
}

@Composable
private fun ErrorPlaceHolder(error: ErrorData) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { error.onClick?.invoke() },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clickable(
                        onClick = { error.onClick?.invoke() }
                    ),
                tint = colorResource(id = R.color.white)
            )
            Text(error.message.asString())
        }
    }
}

@Preview
@Composable
private fun MQScaffoldPreview() {

    val colors = listOf(Color.Blue, Color.Cyan, Color.Green, Color.Magenta, Color.Yellow)
    MQScaffold(
        error = ErrorData(UiText.DynamicString("Error")),
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(count = 10) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(colors[it % colors.size]),
                    )
                }
            }
        },
    )
}

@Preview
@Composable
private fun MQScaffold_loading_Preview() {

    val colors = listOf(Color.Blue, Color.Cyan, Color.Green, Color.Magenta, Color.Yellow)
    MQScaffold(
        error = ErrorData(UiText.DynamicString("Error")),
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(count = 10) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(colors[it % colors.size]),
                    )
                }
            }
        },
    )
}