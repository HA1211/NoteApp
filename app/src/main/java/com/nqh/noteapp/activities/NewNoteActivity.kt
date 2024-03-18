package com.nqh.noteapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nqh.noteapp.AppDatabase
import com.nqh.noteapp.DateUtils
import com.nqh.noteapp.NoteEntity
import com.nqh.noteapp.databinding.ActivityNewNoteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewNoteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getInstance(this@NewNoteActivity).appDao.addNote(
                    NoteEntity(
                        0,
                        binding.edtTitleDetails.text.toString(),
                        binding.edtContentDetails.text.toString(),
                        DateUtils.getDate()
                    )
                )
            }
            finish()
        }
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}