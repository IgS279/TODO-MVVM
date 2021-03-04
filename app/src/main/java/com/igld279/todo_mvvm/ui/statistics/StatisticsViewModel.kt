package com.igld279.todo_mvvm.ui.statistics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.igld279.todo_mvvm.db.AppDatabase
import com.igld279.todo_mvvm.db.MyNoteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StatisticsViewModel(application: Application) : AndroidViewModel(application) {

    private val db: AppDatabase = AppDatabase.getDatabase(application)
    private val myNoteDao: MyNoteDao = db.myNoteDao()

    private var textAll = MutableLiveData<String>()
    private val textActive = MutableLiveData<String>()
    private val textCompleted = MutableLiveData<String>()



    fun textAll(): LiveData<String> {
        CoroutineScope(Dispatchers.Main).launch{
            textAll.value =myNoteDao.getAll().size.toString()
            }
        return textAll
    }

    fun textActive(): LiveData<String>{
        CoroutineScope(Dispatchers.Main).launch {
            textActive.value = myNoteDao.getAllActiveOrCompleted(false).size.toString()
        }
        return textActive
    }

    fun textCompleted(): LiveData<String> {
        CoroutineScope(Dispatchers.Main).launch {
            textCompleted.value = myNoteDao.getAllActiveOrCompleted(true).size.toString()
        }
        return textCompleted
    }
}