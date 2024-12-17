package com.gems.todos.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gems.todos.navigation.NavigationItem
import com.gems.todos.ui.retrofit.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreen(navController: NavController, taskId: Int?, tasks: List<Task>, isLoading: Boolean) {
    val task = tasks.find { it.id == taskId }

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            navController.navigate(NavigationItem.TodoScreen.route) {
                                popUpTo(NavigationItem.TodoScreen.route) { inclusive = false }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null
                            )
                        }
                        Text(text = "Details", modifier = Modifier.padding(15.dp, 0.dp))
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(15.dp)
        ) {
            if (isLoading) {
                Text("Loading...", modifier = Modifier.padding(16.dp))
            } else if (task == null) {
                Text("Task not found", modifier = Modifier.padding(16.dp))
            } else {
                OutlinedTextField(
                    value = task.title,
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 130.dp),
                    maxLines = Int.MAX_VALUE,
                    readOnly = true
                )

                Text(
                    "Status: ${if (task.completed) "Completed" else "Pending"}",
                    modifier = Modifier.padding(8.dp),
                    color = if (task.completed) Color.Green else Color.Red
                )
                Text(
                    "Task ID: ${task.id}",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
