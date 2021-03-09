package com.igld279.todo_mvvm.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class MyNote(
        @PrimaryKey (autoGenerate = true) val uid: Int?,
        @ColumnInfo(name = "titleText") val titleText: String?,
        @ColumnInfo(name = "todoText") val todoText: String?,
        @ColumnInfo(name = "done") var done: Boolean = false,
    ) : Serializable