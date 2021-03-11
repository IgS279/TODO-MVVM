package com.igld279.todo_mvvm.ui.notes

import android.app.Application
import androidx.lifecycle.*
import com.igld279.todo_mvvm.db.AppDatabase
import com.igld279.todo_mvvm.db.MyNote
import com.igld279.todo_mvvm.db.MyNoteDao

class NotesViewModel(application: Application, private val myNoteDao: MyNoteDao)
    : AndroidViewModel(application) {

    fun getNotesLiveData() = myNoteDao.getAllLiveData()

    suspend fun getAllNotes() = myNoteDao.getAll()

    suspend fun getAllNotesActive() = myNoteDao.getAllActiveOrCompleted(false)

    suspend fun getAllNotesCompleted() = myNoteDao.getAllActiveOrCompleted(true)


    suspend fun deleteNote(myNote: MyNote?) = myNoteDao.delete(myNote)

    suspend fun updateNote(myNote: MyNote) = myNoteDao.update(myNote)

}