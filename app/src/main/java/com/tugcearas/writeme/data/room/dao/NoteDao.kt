package com.tugcearas.writeme.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tugcearas.writeme.data.room.model.NoteEntity

@Dao
interface NoteDao {

    @Query("Select * From Notes")
    fun getNotes():LiveData<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes:NoteEntity)

    @Query("Delete From Notes Where id=:id")
    fun deleteNotes(id:Int)

    @Update
    fun updateNotes(notes:NoteEntity)
}