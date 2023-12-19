package com.nqh.noteapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    version = 1,
    entities = [
        NoteEntity::class
    ]
)

abstract class AppDatabase :RoomDatabase() {

    abstract val appDao : DaoInterface

    //khởi tạo nó trong chính nó
    companion object {

        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {

            return INSTANCE?: synchronized(this) {
                val intan = Room.databaseBuilder(
                    context, AppDatabase::class.java, "room_note_1"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = intan
                return intan
            }
        }

    }
}