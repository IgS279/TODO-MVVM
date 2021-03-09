package com.igld279.todo_mvvm.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.igld279.todo_mvvm.R
import com.igld279.todo_mvvm.db.MyNote

class CustomAdapter(private var myNotes: List<MyNote>,
                    private val listener: OnCustomClickListener)
    : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewTaskOne: TextView = itemView.findViewById(R.id.textViewNoteOne)
        val checkBoxTaskOne: CheckBox = itemView.findViewById(R.id.checkBoxNoteOne)
        val constraintLayoutOne: ConstraintLayout = itemView.findViewById(R.id.constraintLayoutOne)
    }

    fun refreshNotes(myNotes: List<MyNote>) {
        this.myNotes = myNotes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.one, parent, false)
        return ViewHolder(itemView)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textViewTaskOne.text = myNotes[position].titleText

        val currentItemCheckBox: Boolean = myNotes[position].done
        holder.checkBoxTaskOne.isChecked = currentItemCheckBox

        holder.constraintLayoutOne.setOnClickListener{
            listener.onItemClick(myNotes[position], position)
        }

        holder.checkBoxTaskOne.setOnClickListener {
            listener.onItemCheckBoxClick(it, myNotes[position])
        }

        holder.constraintLayoutOne.setOnCreateContextMenuListener { contextMenu, view, contextMenuInfo ->

            contextMenu?.add("Edit")?.setOnMenuItemClickListener {
                listener.onMenuItemClickListenerEdit(myNotes[position])
                true
            }
            contextMenu?.add("Delete")?.setOnMenuItemClickListener {
                listener.onMenuItemClickListenerDelete(myNotes[position])
                true
            }
        }
    }

    override fun getItemCount(): Int = myNotes.size
}