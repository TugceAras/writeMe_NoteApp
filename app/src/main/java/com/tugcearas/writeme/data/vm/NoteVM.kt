package com.tugcearas.writeme.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tugcearas.writeme.data.repository.NoteRepository
import com.tugcearas.writeme.data.room.database.NoteDatabase
import com.tugcearas.writeme.data.room.model.NoteEntity

class NoteVM(application: Application):AndroidViewModel(application) {

    val repo:NoteRepository

    init {
        val dao = NoteDatabase.getDatabaseInstance(application).noteDao()
        repo= NoteRepository(dao)
    }

    fun addNotes(notes:NoteEntity){
        repo.insertNote(notes)
    }

    fun getAllNotes():LiveData<List<NoteEntity>> = repo.getAllNotes()

    fun deleteNotes(id:Int){
        repo.deleteNote(id)
    }

    fun updateNotes(notes:NoteEntity){
        repo.updateNote(notes)
    }
}