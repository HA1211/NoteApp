package com.nqh.noteapp.activities

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nqh.noteapp.AppDatabase
import com.nqh.noteapp.DateUtils
import com.nqh.noteapp.NoteEntity
import com.nqh.noteapp.R
import com.nqh.noteapp.database.OnClickNote
import com.nqh.noteapp.databinding.ActivityDetailsBinding
import com.nqh.noteapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getInstance(this@DetailsActivity).appDao.addNote(
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