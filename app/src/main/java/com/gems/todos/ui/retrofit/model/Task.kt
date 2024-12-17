package com.gems.todos.ui.retrofit.model

data class Task(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)