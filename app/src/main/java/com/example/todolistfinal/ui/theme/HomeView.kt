package com.example.todolistfinal.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todolistfinal.R
import com.example.todolistfinal.Screen
import com.example.todolistfinal.TodoList
import com.example.todolistfinal.TodoViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    controller: NavHostController,
    viewModel: TodoViewModel
){

    Scaffold (
        backgroundColor = colorResource(id = R.color.light_gray),
        topBar = {
            AppTopBar(title = "TodoList") {
                // TODO nothing
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                // TODO navigate to Add the todo list
                          controller.navigate(Screen.AddUpdateView.route + "/0L")
                },
                modifier = Modifier.padding(10.dp),
                backgroundColor = colorResource(id = R.color.light_blue)
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = null,
                    tint = colorResource(id = R.color.white),
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    ){

        val todoList = viewModel.getAllWorks.collectAsState(initial = listOf())

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ){

            items(todoList.value , key = {work -> work.id}){
                item ->

                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if(it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd){
                                viewModel.deleteAWork(item)
                            }
                            true
                        }
                    )

                    SwipeToDismiss(
                        state = dismissState,
                        background = {
                            val backgroundColor by animateColorAsState(
                                if(dismissState.dismissDirection == DismissDirection.EndToStart)
                                    Color.Red
                                else
                                    Color.Transparent,
                                label = ""
                            )
                            
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp)
                                .background(backgroundColor),
                                contentAlignment = Alignment.CenterEnd
                            ){
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null,
                                    tint = Color.White,
                                )
                            }
                        },
                        dismissContent = {
                            TodoItem(item = item) {
                                val id = item.id
                                controller.navigate(Screen.AddUpdateView.route + "/$id")
                            }
                        },
                        dismissThresholds = {FractionalThreshold(0.25f)},
                        directions = setOf(DismissDirection.EndToStart)
                    )

            }
        }
    }
}

@Composable
fun TodoItem(
    item : TodoList,
    onclickTodoItem : () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable {
                onclickTodoItem()
            },
        elevation = 10.dp,
        backgroundColor = colorResource(id = R.color.card_bg)
    ) {
        Column (
            modifier = Modifier.padding(15.dp)
        ){
            Text(
                text = item.todoWork,
                color = colorResource(id = R.color.white),
                fontSize = 18.sp
            )
        }
    }
}