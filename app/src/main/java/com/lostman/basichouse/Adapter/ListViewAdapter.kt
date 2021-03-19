package com.lostman.basichouse.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.lostman.basichouse.R
import com.lostman.basichouse.Util.saveData
import com.lostman.basichouse.Util.saveDataList
import com.lostman.basichouse.Vo.MemoVo
import org.jetbrains.anko.find

class ListViewAdapter(val items:MutableList<MemoVo>):BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
         return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        val convertView = LayoutInflater.from(parent?.context).inflate(R.layout.memo_item, parent, false)

        val item = items[position]
        convertView!!.find<TextView>(R.id.memoTitle).text = item.title
        convertView!!.find<TextView>(R.id.memoContent).text = item.memo
        convertView!!.find<TextView>(R.id.memoDate).text = item.date
        val delBtn = convertView.findViewById<Button>(R.id.memoDelBtn)
        delBtn.setOnClickListener {
            items.removeAt(position)
            saveDataList(items ,convertView.context)
            notifyDataSetChanged()
        }
        return convertView

    }

    fun refresh(){
        this.notifyDataSetChanged()
    }
}