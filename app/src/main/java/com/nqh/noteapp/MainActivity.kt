package com.nqh.noteapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import com.nqh.noteapp.adapter.NoteAdapter
import com.nqh.noteapp.adapter.OnClickNote
import com.nqh.noteapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnClickNote {

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        NoteAdapter(this@MainActivity, ArrayList(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcyNote.adapter = adapter

        getData()

    }

    /*private fun add(){
        CoroutineScope(Dispatchers.IO).launch {
            val data = AppDatabase.getInstance(this@MainActivity).appDao.addNote(
                NoteEntity(0, "afb","ajkfbs", "aksjd")
            )
        }
    }*/

    private fun getData(){
        CoroutineScope(Dispatchers.IO).launch {
            val data = AppDatabase.getInstance(this@MainActivity).appDao.getAllNote()
            adapter.setData(data as ArrayList<NoteEntity>)

        }
    }

    override fun clickNote(position: Int, noteEntity: NoteEntity) {

    }
}

/*CoroutineScope(Dispatchers.IO).launch { //IO là tên luồng
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
        }*/