package com.example.todolistfinal

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val todoWorkRepository: TodoWorkRepository = Graph.todoRepository
) : ViewModel() {

    var todoWorkState by mutableStateOf("")

    fun onTodoWorkChange(newString : String){
        todoWorkState = newString
    }

    lateinit var getAllWorks : Flow<List<TodoList>>

    init {
        viewModelScope.launch {
            getAllWorks = todoWorkRepository.getAllWorks()
        }
    }

    fun addAWork(workEntity : TodoList){
        viewModelScope.launch {
            todoWorkRepository.addAWork(workEntity)
        }
    }

    fun updateAWork(workEntity : TodoList){
        viewModelScope.launch {
            todoWorkRepository.updateAWork(workEntity)
        }
    }

    fun deleteAWork(workEntity : TodoList){
        viewModelScope.launch {
            todoWorkRepository.deleteAWork(workEntity)
        }
    }

    fun getAWorkById(id : Long) : Flow<TodoList>{
        return todoWorkRepository.getAWorkById(id)
    }

}