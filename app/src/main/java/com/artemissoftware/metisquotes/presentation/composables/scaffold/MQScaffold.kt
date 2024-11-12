package com.artemissoftware.metisquotes.presentation.composables.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.artemissoftware.metisquotes.presentation.composables.loading.LoadingProgress
import kotlin.math.roundToInt

@Composable
fun MQScaffold(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    //error: ErrorData? = null,
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

//        error?.let {
//            PlaceHolderContent(
//                icon = R.drawable.ic_pokeball,
//                message = it.message.asString(),
//                onClick = it.onClick,
//                buttonText = it.buttonText.asString(),
//            )
//        }
    }
}

@Preview
@Composable
private fun PCScaffoldPreview() {

    val colors = listOf(Color.Blue, Color.Cyan, Color.Green, Color.Magenta, Color.Yellow)
    MQScaffold(
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