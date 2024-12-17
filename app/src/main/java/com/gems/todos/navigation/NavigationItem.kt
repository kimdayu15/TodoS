package com.gems.todos.navigation


sealed class NavigationItem (val route: String){
    object TodoScreen : NavigationItem("todoScreen")
    object ItemScreen : NavigationItem("itemScreen")
}