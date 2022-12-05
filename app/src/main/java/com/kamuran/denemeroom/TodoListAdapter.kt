package com.kamuran.denemeroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoListAdapter constructor (private var todoList:ArrayList<ModelTodo>,
                                   private val mItemclickListener:ItemClickListener
):RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(){
   inner class TodoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

       val deleteBtn= itemView.findViewById<ImageView>(R.id.todo_delete_btn)
       val updateBtn= itemView.findViewById<ImageView>(R.id.todo_edit_btn)

       init {
           deleteBtn.setOnClickListener{
               deleteItemPosition=position
               mItemclickListener.deleteItem(todoList[position])
           }
           updateBtn.setOnClickListener {
               updateItemPosition=position
               mItemclickListener.updateItem(todoList[position])
           }
       }
    }

    fun itemAdded(modelTodo: ModelTodo){
        todoList.add(modelTodo)
        notifyDataSetChanged()
    }

    fun itemDeleted(modelTodo: ModelTodo){
        todoList.remove(modelTodo)
        notifyItemRemoved(deleteItemPosition)
    }

    fun itemUpdated(modelTodo: ModelTodo){
        todoList[updateItemPosition]=modelTodo
        notifyItemChanged(updateItemPosition)
    }

    interface ItemClickListener {
        fun deleteItem(todoModel:ModelTodo)
        fun updateItem(todoModel: ModelTodo)

    }


    private var deleteItemPosition:Int=-1
    private var updateItemPosition:Int=-1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_row_todo,parent,false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todoNametv= holder.itemView.findViewById<TextView>(R.id.todo_tv)
        val rowIdtv=holder.itemView.findViewById<TextView>(R.id.tv_ıd)

        todoNametv.text=todoList[position].todoName
        rowIdtv.text=(position+1).toString()
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}