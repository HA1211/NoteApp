package com.nqh.noteapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nqh.noteapp.NoteEntity
import com.nqh.noteapp.R
import java.text.FieldPosition

class NoteAdapter(
    val context: Context,
    val listData: ArrayList<NoteEntity>,
    val listener: OnClickNote
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val viewAll : LinearLayout = view.findViewById(R.id.viewAll)
        val txtTitle : TextView = view.findViewById(R.id.txtTitle)
        val txtContent : TextView = view.findViewById(R.id.txtContent)
        val txtDate : TextView = view.findViewById(R.id.txtDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listData[position]


        //hỏi
        holder.viewAll.setOnClickListener{
            listener.clickNote(holder.viewAll, position, item)
        }

        holder.txtTitle.text = item.title
        holder.txtContent.text = item.content
        holder.txtDate.text = item.date

    }

    fun setData(listData: ArrayList<NoteEntity>){
        this.listData.clear()
        this.listData.addAll(listData)
        notifyDataSetChanged()
    }
}

interface OnClickNote {
    fun clickNote(view:View,position: Int, noteEntity: NoteEntity)
}