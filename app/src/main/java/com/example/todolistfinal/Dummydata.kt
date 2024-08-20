package com.example.todolistfinal

import android.content.Context
import androidx.room.Room

object Graph{

   lateinit var database : TodoDatabase

    val todoRepository by lazy {
        TodoWorkRepository(todoWorkDao = database.TodoDao())
    }

    fun provide(context : Context){
        database = Room.databaseBuilder(context,TodoDatabase::class.java,"todolist.dp").build()
    }
}