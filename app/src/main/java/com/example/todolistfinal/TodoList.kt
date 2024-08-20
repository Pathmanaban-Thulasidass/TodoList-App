package com.example.todolistfinal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("todo-table")
data class TodoList (
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    @ColumnInfo("todo-work")
    val todoWork : String
)