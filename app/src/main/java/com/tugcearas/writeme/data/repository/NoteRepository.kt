package com.tugcearas.writeme.data.repository

import androidx.lifecycle.LiveData
import com.tugcearas.writeme.data.room.dao.NoteDao
import com.tugcearas.writeme.data.room.model.NoteEntity

class NoteRepository(val dao:NoteDao) {

    fun getAllNotes():LiveData<List<NoteEntity>> {
        return dao.getNotes()
    }

    fun insertNote(notes:NoteEntity){
        return dao.insertNotes(notes)
    }

    fun deleteNote(id:Int){
        dao.deleteNotes(id)
    }

    fun updateNote(notes:NoteEntity){
        dao.updateNotes(notes)
    }
}