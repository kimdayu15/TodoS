package com.gems.todos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gems.todos.ui.retrofit.model.Task
import com.gems.todos.ui.screen.ItemScreen
import com.gems.todos.ui.screen.TodoScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    tasks: List<Task>,
    isLoading: Boolean,
    startDestination: String = NavigationItem.TodoScreen.route
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.TodoScreen.route) {
            TodoScreen(navController = navController, tasks = tasks, isLoading = isLoading)
        }

        composable("itemScreen/{id}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            ItemScreen(navController, tasks = tasks, isLoading = isLoading, taskId = taskId)
        }
    }
}

