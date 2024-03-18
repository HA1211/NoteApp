package com.nqh.noteapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nqh.noteapp.AppDatabase
import com.nqh.noteapp.DateUtils
import com.nqh.noteapp.NoteEntity
import com.nqh.noteapp.R
import com.nqh.noteapp.databinding.ActivityDetailBinding
import com.nqh.noteapp.databinding.ActivityDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    private lateinit var old_note : NoteEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        old_note = intent.getSerializableExtra("note_data") as NoteEntity



        binding.edtTitleDetail.setText(old_note.title)
        binding.edtContentDetail.setText(old_note.content)

        binding.btnDone.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getInstance(this@DetailActivity).appDao.addNote(
                    NoteEntity(
                        old_note.id,
                        binding.edtTitleDetail.text.toString(),
                        binding.edtContentDetail.text.toString(),
                        DateUtils.getDate()
                    )
                )
            }
            finish()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

    }
}