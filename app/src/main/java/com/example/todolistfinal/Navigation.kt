package com.example.todolistfinal

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolistfinal.ui.theme.HomeView

@Composable
fun Navigation(
    controller : NavController,
    viewModel : TodoViewModel
){

    NavHost(
        navController = controller as NavHostController, 
        startDestination = Screen.HomeView.route)
    {

        composable(Screen.HomeView.route){
            HomeView(controller = controller, viewModel = viewModel)
        }

        composable(
            Screen.AddUpdateView.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
            ){
            val id = if(it.arguments != null) it.arguments!!.getLong("id") else 0L
            AddUpdateView(id = id, controller = controller, viewModel = viewModel)
        }
    }
}