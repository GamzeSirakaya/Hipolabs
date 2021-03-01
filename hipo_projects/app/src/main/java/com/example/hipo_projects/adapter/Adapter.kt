package com.example.hipo_projects.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hipo_projects.R
import com.example.hipo_projects.databinding.RecyclerRowBinding
import com.example.hipo_projects.model.Position

class Adapter(val positionlist: ArrayList<Position>) :
    RecyclerView.Adapter<Adapter.PositionViewHolder>() {

    class PositionViewHolder(var view: RecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.PositionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerRowBinding>(
            inflater,
            R.layout.recycler_row, parent, false
        )
        return PositionViewHolder(view)

    }

    override fun onBindViewHolder(holder: Adapter.PositionViewHolder, position: Int) {
        holder.view.position = positionlist[position]
        holder.view.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return positionlist.size
    }

    fun update(newList: List<Position>) {
        positionlist.clear()
        positionlist.addAll(newList)
        notifyDataSetChanged()
    }
}