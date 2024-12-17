package com.gems.todos.ui.retrofit

import com.gems.todos.ui.retrofit.model.Task
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("todos/")
    fun getTaskList(): Call<MutableList<Task>>

}