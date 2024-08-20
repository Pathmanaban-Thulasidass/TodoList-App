package com.example.todolistfinal

import kotlinx.coroutines.flow.Flow

class TodoWorkRepository(private val todoWorkDao : TodoWorkDao) {

    suspend fun addAWork(workEntity : TodoList){
        todoWorkDao.addAWork(workEntity)
    }

    fun getAllWorks() : Flow<List<TodoList>> = todoWorkDao.getAllWork()

    suspend fun updateAWork(workEntity: TodoList){
        todoWorkDao.updateWork(workEntity)
    }

    suspend fun deleteAWork(workEntity: TodoList) {
        todoWorkDao.deleteWork(workEntity)
    }

    fun getAWorkById(id : Long) : Flow<TodoList> = todoWorkDao.getAWorkById(id)
}