package com.example.todolistfinal.ui.theme

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.todolistfinal.R

@Composable
fun AppTopBar(
    title : String,
    onNavBackClicked : () -> Unit
){

    val navigationIcon : (@Composable () -> Unit) = {
        if(!title.equals("TodoList")){
            IconButton(
                onClick = {
                    onNavBackClicked()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
    }


    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = colorResource(id = R.color.light_blue),
        elevation = 3.dp,
        navigationIcon = navigationIcon
    )
}