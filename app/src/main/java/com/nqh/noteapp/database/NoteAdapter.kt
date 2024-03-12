package com.nqh.noteapp.database

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nqh.noteapp.NoteEntity
import com.nqh.noteapp.R

class NoteAdapter(
    private val context: Context,
    private val listData: ArrayList<NoteEntity>,
    private val listener: OnClickNote
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewAll: LinearLayout = view.findViewById(R.id.viewAll)
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtContent: TextView = view.findViewById(R.id.txtContent)
        val txtDate: TextView = view.findViewById(R.id.txtDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {/*
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)*/
        //khai báo context ở trên rồi thì không cần parent.context nữa
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listData[position]

        //hỏi
        holder.viewAll.setOnLongClickListener {
            listener.clickNote(holder.viewAll, position, item)
            true
        }

        holder.txtTitle.text = item.title
        holder.txtContent.text = item.content
        holder.txtDate.text = item.date

        //random màu cho note
        /*holder.viewAll.setBackgroundColor(holder.viewAll.resources.getColor(randomColor(), null))*/
    }


    //hàm cập nhật dữ liệu
    fun setData(listData: ArrayList<NoteEntity>) {
        this.listData.clear()
        this.listData.addAll(listData)
        notifyDataSetChanged() //thông báo cho adapter dữ liệu đã thay đổi
    }

    /*fun randomColor() : Int{
        val list = ArrayList<Int>()
        list.add(R.color.NoteColor1)
        list.add(R.color.NoteColor2)
        list.add(R.color.NoteColor3)
        list.add(R.color.NoteColor4)
        list.add(R.color.NoteColor5)
        list.add(R.color.NoteColor6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)

        return list[randomIndex]
    }*/
}

interface OnClickNote {
    fun clickNote(view: View, position: Int, noteEntity: NoteEntity)
}