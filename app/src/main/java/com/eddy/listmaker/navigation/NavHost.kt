package com.eddy.listmaker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eddy.listmaker.views.TaskListScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.TaskListScreen.route) {
        composable(Screens.TaskListScreen.route) {
            TaskListScreen()
        }
    }
}