package com.example.todolistfinal

sealed class Screen(val route : String){

    object HomeView : Screen("home_view")
    object AddUpdateView : Screen("add_view")

}