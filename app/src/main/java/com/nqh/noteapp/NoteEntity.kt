package com.nqh.noteapp

import  android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "note")
data class NoteEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "title")
    val title : String,

    @ColumnInfo(name = "content")
    val content : String,

    @ColumnInfo(name = "date")
    val date : String

) : Parcelable