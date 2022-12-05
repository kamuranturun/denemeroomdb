package com.kamuran.denemeroom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoTodo {

@Insert
fun addTodo(todo:ModelTodo)

@Query("SELECT*FROM todo_tabe ORDER BY todo_id DESC")
fun getAllTodo():List<ModelTodo>

@Query("UPDATE todo_tabe SET todo_name=:todoName WHERE todo_id=:todoId")
fun updateTodo(todoName:String, todoId:Int)


@Delete
fun delete(todo:ModelTodo)
}