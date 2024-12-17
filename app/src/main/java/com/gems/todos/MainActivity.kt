package com.gems.todos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.gems.todos.navigation.AppNavHost
import com.gems.todos.ui.retrofit.RetrofitClient
import com.gems.todos.ui.retrofit.RetrofitService
import com.gems.todos.ui.retrofit.model.Task
import com.gems.todos.ui.screen.TodoScreen
import com.gems.todos.ui.theme.TodoSTheme
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val tasks = remember { mutableStateOf<List<Task>>(emptyList()) }
            val isLoading = remember { mutableStateOf(true) }
            val retrofitService: RetrofitService =
                RetrofitClient.getClient().create(RetrofitService::class.java)
            TodoSTheme {

                LaunchedEffect(Unit) {
                    retrofitService.getTaskList().enqueue(object : Callback<MutableList<Task>> {
                        override fun onResponse(
                            call: Call<MutableList<Task>>,
                            response: Response<MutableList<Task>>
                        ) {
                            if (response.isSuccessful && response.body() != null) {
                                tasks.value = response.body()!!
                                isLoading.value = false
                            }
                        }

                        override fun onFailure(call: Call<MutableList<Task>>, t: Throwable) {
                            Log.d("TAG", t.message.toString())
                            isLoading.value = false
                        }
                    })
                }

                TodoSTheme {
                    val navController = rememberNavController()
                    AppNavHost(
                        navController = navController,
                        tasks = tasks.value,
                        isLoading = isLoading.value
                    )
                }

            }
        }
    }
}
