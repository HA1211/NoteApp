package com.nqh.noteapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DaoInterface {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //conFlick : xung đột thì replace
    fun addNote(noteEntity: NoteEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addListNote(noteEntity: ArrayList<NoteEntity>): MutableList<Long>

    @Query("SELECT * from note")
    fun getAllNote(): MutableList<NoteEntity>

    @Query("SELECT * from note where title = :str")
    fun getNoteByName(str: String): MutableList<NoteEntity>

    @Delete
    fun deleteNote(noteEntity: NoteEntity): Int

    //xóa theo id
    @Query("DELETE from note where id = :id")
    fun deleteNoteBy(id: Int): Int

    @Query("SELECT * from note")
    fun getAll(): LiveData<MutableList<NoteEntity>>
}