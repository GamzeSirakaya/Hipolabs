package com.example.hipo_intern.adapter

import Member
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.hipo_intern.R
import com.example.hipo_intern.view.Fragments.ListMemberFragmentDirections
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.recycler_row.view.*

class MemberAdapter(val listMember: ArrayList<Member>) :
    RecyclerView.Adapter<MemberAdapter.MemberListViewHolder>() {

    private val gson = GsonBuilder().create()

    class MemberListViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row, parent, false)
        return MemberListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberListViewHolder, position: Int) {
        holder.view.apply {
            member_name.text = listMember[position].name
            member_position.text = listMember[position].hipo.position
            member_location.text = listMember[position].location
        }
        holder.view.setOnClickListener {
            action(it, position)
        }
    }

    override fun getItemCount(): Int {
        return listMember.size
    }

    fun update(newListMember: List<Member>) {
        listMember.clear()
        listMember.addAll(newListMember)
        notifyDataSetChanged()

    }

    fun action(view: View, position: Int) {
        val action = ListMemberFragmentDirections.actionListMemberFragmentToDetailMemberFragment(
            gson.toJson(listMember[position])
        )
        Navigation.findNavController(view).navigate(action)

    }
}