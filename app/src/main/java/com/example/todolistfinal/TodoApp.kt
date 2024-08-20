package com.example.todolistfinal

import android.app.Application

class TodoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}