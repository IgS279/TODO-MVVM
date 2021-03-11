package com.igld279.todo_mvvm

import android.app.Application
import com.igld279.todo_mvvm.di.databaseModule
import com.igld279.todo_mvvm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(viewModelModule, databaseModule)
            )
        }
    }
}