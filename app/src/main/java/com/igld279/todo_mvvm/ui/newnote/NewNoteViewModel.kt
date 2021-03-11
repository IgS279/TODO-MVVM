package com.igld279.todo_mvvm.ui.newnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.igld279.todo_mvvm.db.AppDatabase
import com.igld279.todo_mvvm.db.MyNote
import com.igld279.todo_mvvm.db.MyNoteDao

class NewNoteViewModel(application: Application, private val myNoteDao: MyNoteDao)
    : AndroidViewModel(application) {

    suspend fun insert(myNote: MyNote) = myNoteDao.insert(myNote)

    suspend fun update(myNote: MyNote) = myNoteDao.update(myNote)

}