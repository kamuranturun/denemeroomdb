package com.kamuran.denemeroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_tabe")
data class ModelTodo(
@PrimaryKey(autoGenerate = true)

@ColumnInfo(name = "todo_id")
var todoId:Int=0,

@ColumnInfo(name = "todo_name")
var todoName:String,
)
