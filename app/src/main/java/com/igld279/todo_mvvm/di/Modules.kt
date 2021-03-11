package com.igld279.todo_mvvm.di

import android.app.Application
import androidx.room.Room
import com.igld279.todo_mvvm.db.AppDatabase
import com.igld279.todo_mvvm.db.MyNoteDao
import com.igld279.todo_mvvm.ui.info.InfoViewModel
import com.igld279.todo_mvvm.ui.newnote.NewNoteViewModel
import com.igld279.todo_mvvm.ui.notes.NotesViewModel
import com.igld279.todo_mvvm.ui.statistics.StatisticsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NotesViewModel(get(), get()) }
    viewModel { NewNoteViewModel(get(), get()) }
    viewModel { StatisticsViewModel(get(), get()) }
    viewModel { InfoViewModel() }
}

val databaseModule = module {
    fun provideAppDatabase(application: Application): AppDatabase {
        return AppDatabase.getDatabase(application)
    }

    fun provideMyNoteDao(db: AppDatabase): MyNoteDao {
        return db.myNoteDao()
    }

    single { provideAppDatabase(get()) }
    single { provideMyNoteDao(get()) }
}