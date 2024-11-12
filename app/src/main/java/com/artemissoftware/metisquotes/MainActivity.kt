package com.artemissoftware.metisquotes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.metisquotes.presentation.composables.navbar.MQBottomNavigationBar
import com.artemissoftware.metisquotes.presentation.navigation.RootNavGraph
import com.artemissoftware.metisquotes.presentation.navigation.Route
import com.artemissoftware.metisquotes.ui.theme.MetisQuotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MetisQuotesTheme {
                QuoteApp()
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun QuoteApp() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            MQBottomNavigationBar(navController = navController)
        },
        content = {  innerPadding ->
            RootNavGraph(
                navController = navController,
                startDestination = Route.DailyQuotes.route
            )

        }
    )
}

@Preview(showBackground = true)
@Composable
private fun QuoteAppPreview() {
    MetisQuotesTheme {
        QuoteApp()
    }
}