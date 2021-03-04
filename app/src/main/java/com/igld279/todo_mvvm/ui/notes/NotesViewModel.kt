package com.igld279.todo_mvvm.ui.notes

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.*
import com.igld279.todo_mvvm.db.AppDatabase
import com.igld279.todo_mvvm.db.MyNote
import com.igld279.todo_mvvm.db.MyNoteDao

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val db: AppDatabase = AppDatabase.getDatabase(application)
    private val myNoteDao: MyNoteDao = db.myNoteDao()

    fun getNotesLiveData() = myNoteDao.getAllLiveData()

    suspend fun getAllNotes() = myNoteDao.getAll()

    suspend fun getAllNotesActive() = myNoteDao.getAllActiveOrCompleted(false)

    suspend fun getAllNotesCompleted() = myNoteDao.getAllActiveOrCompleted(true)


    suspend fun deleteNote(myNote: MyNote?) = myNoteDao.delete(myNote)

    suspend fun updateNote(myNote: MyNote) = myNoteDao.update(myNote)






}