package com.tugcearas.writeme.data.room.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Notes")
data class NoteEntity(

    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,

    val title:String,
    val subtitle:String,
    val note:String,
    val date:String

):Parcelable
