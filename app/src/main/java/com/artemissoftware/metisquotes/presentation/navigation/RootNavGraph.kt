package com.artemissoftware.metisquotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artemissoftware.metisquotes.presentation.allquotes.AllQuotesScreen
import com.artemissoftware.metisquotes.presentation.dailyquotes.DailyQuotesScreen
import com.artemissoftware.metisquotes.presentation.randomquotes.RandomQuoteScreen

const val ROOT_GRAPH = "root_graph"

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        route = ROOT_GRAPH,
        startDestination = startDestination,
    ) {

        composable(Route.DailyQuotes.route) {
            DailyQuotesScreen()
        }

        composable(Route.RandomQuote.route) {
            RandomQuoteScreen()
        }

        composable(Route.AllQuotes.route) {
            AllQuotesScreen()
        }
    }
}