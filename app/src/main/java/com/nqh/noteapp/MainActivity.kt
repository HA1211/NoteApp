package com.nqh.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch { //IO là tên luồng
            AppDatabase.getInstance(this@MainActivity).appDao.addNote(
                NoteEntity(0, "Đi chơi", "Mua cá, mua thịt, mua rau", "16/12/2023")
            )
        }

        CoroutineScope(Dispatchers.IO).launch {
            val data = AppDatabase.getInstance(this@MainActivity).appDao.getAllNote()
            data.forEach {
                Log.e("hiepnq", "${it.id} - ${it.title}" )
            }
            Log.e("hiepnq", "done" )
        }

        CoroutineScope(Dispatchers.IO).launch {
            val data = AppDatabase.getInstance(this@MainActivity).appDao.getNoteByName("Đi học")
            data.forEach {
                Log.e("hiepnq", "${it.id} - ${it.title}" )
            }
            Log.e("hiepnq", "done" )
        }


        //get thằng Đi học xong nếu có thì xóa luôn
        CoroutineScope(Dispatchers.IO).launch {
            val data = AppDatabase.getInstance(this@MainActivity).appDao.getNoteByName("Đi học")
            data.forEach {
                Log.e("hiepnq", "${it.id} - ${it.title}" )
                AppDatabase.getInstance(this@MainActivity).appDao.deleteNote(it)
            }
            Log.e("hiepnq", "done" )
        }


        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getInstance(this@MainActivity).appDao.deleteNoteBy(10)
        }

    }
}