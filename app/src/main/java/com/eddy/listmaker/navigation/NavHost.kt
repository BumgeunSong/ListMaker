package com.eddy.listmaker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eddy.listmaker.views.TaskDetailScreen
import com.eddy.listmaker.views.TaskListScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.TaskListScreen.route) {
        composable(Screens.TaskListScreen.route) {
            TaskListScreen(navigate = { taskListName ->
                navController.navigate("${Screens.TaskDetailScreen.route}/${taskListName}")
            })
        }
        composable(
            route = Screens.TaskDetailScreen.route,
            arguments = listOf(navArgument("taskListName") {
                type = NavType.StringType
            })
        ) {
            TaskDetailScreen(
                taskListName = it.arguments?.getString("taskListName"),
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}