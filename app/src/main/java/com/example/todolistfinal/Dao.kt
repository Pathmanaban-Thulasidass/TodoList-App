package com.example.todolistfinal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TodoWorkDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAWork(WorkEntity : TodoList)

    @Query("select * from `todo-table`")
    abstract fun getAllWork() : Flow<List<TodoList>>

    @Update
    abstract suspend fun updateWork(WorkEntity : TodoList)

    @Delete
    abstract suspend fun deleteWork(WorkEntity: TodoList)

    @Query("select * from `todo-table` where id=:id")
    abstract fun getAWorkById(id:Long) : Flow<TodoList>

}