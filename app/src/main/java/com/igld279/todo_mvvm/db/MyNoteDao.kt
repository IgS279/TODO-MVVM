package com.igld279.todo_mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MyNoteDao {

    @Query("SELECT * FROM MyNote")
    fun getAllLiveData(): LiveData<List<MyNote>>

    @Query("SELECT * FROM MyNote")
    suspend fun getAll(): List<MyNote>

    @Query("SELECT * FROM MyNote WHERE done IN (:done)")
    suspend fun getAllActiveOrCompleted(done: Boolean): List<MyNote>

    @Delete
    suspend fun delete(myNote: MyNote?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myNote: MyNote)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(myNote: MyNote)

}