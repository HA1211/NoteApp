package com.nqh.noteapp

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
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

        binding.imgNew.setOnClickListener {
            showDialogAdd()
        }



    }

    /*private fun add(){
        CoroutineScope(Dispatchers.IO).launch {
            val data = AppDatabase.getInstance(this@MainActivity).appDao.addNote(
                NoteEntity(0, "afb","ajkfbs", "aksjd")
            )
        }
    }*/

    private fun showDialogAdd(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_add_note)
        dialog.setCancelable(false)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val edtTitle = dialog.findViewById<EditText>(R.id.edtTitle)
        val edtContent = dialog.findViewById<EditText>(R.id.edtContent)


        dialog.findViewById<TextView>(R.id.add).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch { //IO là tên luồng
                AppDatabase.getInstance(this@MainActivity).appDao.addNote(
                    NoteEntity(0, edtTitle.text.toString(), edtContent.text.toString(), DateUtils.getDate())
                )
                Handler(Looper.getMainLooper()).post{
                    dialog.dismiss()
                    getData()
                }
            }
        }
        dialog.findViewById<TextView>(R.id.cancel).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun getData(){
        CoroutineScope(Dispatchers.IO).launch {
            val data = AppDatabase.getInstance(this@MainActivity).appDao.getAllNote()
            runOnUiThread{
                adapter.setData(data as ArrayList<NoteEntity>)
            }


        }
    }

    override fun clickNote(view :View,position: Int, noteEntity: NoteEntity) {
        val popupMenu: PopupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.edit ->{
                    showDialogEdit(noteEntity)
                }

                R.id.delete -> {
                    //hiển thị xác nhận xóa
                    CoroutineScope(Dispatchers.IO).launch { //IO là tên luồng
                        AppDatabase.getInstance(this@MainActivity).appDao.deleteNote(noteEntity)
                        getData()
                    }
                }
            }
            true
        })
        popupMenu.show()
    }


    private fun showDialogEdit(noteEntity: NoteEntity){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_add_note)
        dialog.setCancelable(false)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )


        val edtTitle = dialog.findViewById<EditText>(R.id.edtTitle)
        val edtContent = dialog.findViewById<EditText>(R.id.edtContent)
        val add = dialog.findViewById<TextView>(R.id.add)
        val cancel = dialog.findViewById<TextView>(R.id.cancel)

        add.setText("Sửa")
        cancel.setText("Hủy")

        edtTitle.setText(noteEntity.title)
        edtContent.setText(noteEntity.content)

        dialog.findViewById<TextView>(R.id.add).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch { //IO là tên luồng
                AppDatabase.getInstance(this@MainActivity).appDao.addNote(
                    NoteEntity(noteEntity.id, edtTitle.text.toString(), edtContent.text.toString(), DateUtils.getDate())
                )
                Handler(Looper.getMainLooper()).post{
                    dialog.dismiss()
                    getData()
                }
            }
        }
        dialog.findViewById<TextView>(R.id.cancel).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
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