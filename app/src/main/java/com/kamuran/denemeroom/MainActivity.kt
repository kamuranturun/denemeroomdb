package com.kamuran.denemeroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), View.OnClickListener, TodoListAdapter.ItemClickListener {

    private lateinit var todoListRv: RecyclerView
    private lateinit var todoListAdapter: TodoListAdapter

    private lateinit var todoNameEdt: EditText
    private lateinit var addOrUpdateBtn: Button
    private lateinit var databaseTodo: DatabaseTodo
    private var isUpdate: Boolean = false

    private lateinit var updateModelTodo: ModelTodo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoNameEdt = findViewById(R.id.todo_edittext)
        todoListRv = findViewById(R.id.recyclerview_todo)
        addOrUpdateBtn = findViewById(R.id.add_or_update_todo_btn)

        databaseTodo = DatabaseTodo.getTodoDatabase(this)!!

        val todoList = databaseTodo.getTodoDao().getAllTodo()

        todoListRv.layoutManager = LinearLayoutManager(this)
        todoListRv.setHasFixedSize(true)

        todoListAdapter = TodoListAdapter(todoList as ArrayList, this)
        todoListRv.adapter = todoListAdapter

        addOrUpdateBtn.setOnClickListener(this)


    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.add_or_update_todo_btn -> {
                if (isUpdate) {
                    updateTodoItem()
                } else {
                    addTodoItem()
                }
            }
        }
    }

    private fun addTodoItem() {
        val todo = getTodoNameEdt()
        val todoModel= ModelTodo(todoName = todo )

        databaseTodo.getTodoDao().addTodo(todoModel)
        todoListAdapter.itemAdded(todoModel)
        setTodoNameEditText("")
    }

    private fun setTodoNameEditText(text: String) {
        todoNameEdt.setText(text)

    }

    private fun getTodoNameEdt(): String {
        return todoNameEdt.text.toString()
    }

    private fun updateTodoItem() {
     val todoName= getTodoNameEdt()
        databaseTodo.getTodoDao().updateTodo(todoName,updateModelTodo.todoId)
        isUpdate=false
        setAddORUpdateBtnText("ekle")
        setTodoNameEditText("")
        todoListAdapter.itemUpdated(ModelTodo(updateModelTodo.todoId,todoName))
    }


    override fun deleteItem(todoModel: ModelTodo) {
        databaseTodo.getTodoDao().delete(todoModel)
        todoListAdapter.itemDeleted(todoModel)
    }

    override fun updateItem(todoModel: ModelTodo) {

        updateModelTodo= todoModel
        todoNameEdt.setText(todoModel.todoName)
        setAddORUpdateBtnText("güncelle")
        isUpdate=true
    }

    private fun setAddORUpdateBtnText(text: String) {
        addOrUpdateBtn.text=text
    }


}