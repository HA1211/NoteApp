package com.nqh.noteapp

import android.app.Activity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DaoInterface {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //conFlick : xung đột thì replace
    fun addNote(noteEntity: NoteEntity): Long //hàm add luôn trả ra Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addListNote(noteEntity: ArrayList<NoteEntity>): MutableList<Long>

    @Query("SELECT * from note")
    fun getAllNote(): MutableList<NoteEntity>

    @Query("SELECT * from note where title = :str")
    fun getNoteByName(str: String): MutableList<NoteEntity>

    @Delete
    fun deleteNote(noteEntity: NoteEntity): Int

    //xóa theo id thì dùng QUERY
    @Query("DELETE from note where id = :id")
    fun deleteNoteBy(id: Int): Int

}