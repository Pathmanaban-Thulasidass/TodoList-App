package com.example.todolistfinal

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolistfinal.ui.theme.AppTopBar

@Composable
fun AddUpdateView(
    id : Long,
    controller: NavController,
    viewModel: TodoViewModel
){

    val context = LocalContext.current

    if(id != 0L){
        val work = viewModel.getAWorkById(id).collectAsState(initial = TodoList(0L,""))
        viewModel.todoWorkState = work.value.todoWork
    }
    else{
        viewModel.todoWorkState = ""
    }

    Scaffold(
        backgroundColor = colorResource(id = R.color.light_gray),
        topBar = {
            AppTopBar(title = if(id!=0L) "Update Work" else "Add Work"){
                controller.navigateUp()
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TodoTextField(
                label = "Enter Your Work",
                value = viewModel.todoWorkState,
                onValueChanged = {viewModel.onTodoWorkChange(it)},
                pd = it
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    if(viewModel.todoWorkState.isNotBlank()){
                        if(id!=0L){
                            // TODO Update the work
                            viewModel.updateAWork(
                                TodoList(
                                    id = id,
                                    todoWork = viewModel.todoWorkState
                                )
                            )
                        }
                        else{
                            // TODO add the work
                            viewModel.addAWork(
                                TodoList(
                                    todoWork = viewModel.todoWorkState
                                )
                            )
                        }
                        controller.navigateUp()
                        Toast.makeText(context,"Work has Successfully Added",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(context,"Work should not be Empty",Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(
                    text = if(id!=0L) "Update Work" else "Add Work")
            }
        }
    }
}

@Composable
fun TodoTextField(
    label : String,
    value : String,
    onValueChanged : (String) -> Unit,
    pd : PaddingValues
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = colorResource(id = R.color.light_blue),
            focusedLabelColor = Color.White,
            unfocusedLabelColor = colorResource(id = R.color.light_blue),
            cursorColor = colorResource(id = R.color.light_blue)
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        )
    )
}