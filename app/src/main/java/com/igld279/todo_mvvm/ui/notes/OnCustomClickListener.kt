package com.igld279.todo_mvvm.ui.notes

import android.view.View
import com.igld279.todo_mvvm.db.MyNote

interface OnCustomClickListener {

    fun onItemCheckBoxClick(view: View, item: MyNote)
    fun onItemClick(item: MyNote, position: Int)

    fun onMenuItemClickListenerEdit (item: MyNote)
    fun onMenuItemClickListenerDelete (item: MyNote)
}