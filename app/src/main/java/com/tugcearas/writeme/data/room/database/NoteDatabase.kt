package com.tugcearas.writeme.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tugcearas.writeme.data.room.dao.NoteDao
import com.tugcearas.writeme.data.room.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object{
        @Volatile
        var INSTANCE: NoteDatabase?=null

        fun getDatabaseInstance(context:Context):NoteDatabase{
            val temporaryInstance = INSTANCE
            if(temporaryInstance!=null){
                return temporaryInstance
            }
            synchronized(this){
                val roomDbInstance = Room.databaseBuilder(context,NoteDatabase::class.java,"Notes").allowMainThreadQueries().build()
                INSTANCE = roomDbInstance
                return roomDbInstance
            }
        }
    }
}