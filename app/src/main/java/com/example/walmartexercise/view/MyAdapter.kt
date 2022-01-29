package com.example.walmartexercise.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartexercise.R
import com.example.walmartexercise.model.Products

class MyAdapter(val items: List<Products>) : RecyclerView.Adapter<MyAdapter.ViewHolder?>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemText = items[position].productName
        holder.tvItem.text = itemText
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvItem: TextView = itemView.findViewById<View>(R.id.tv_item) as TextView

    }
}