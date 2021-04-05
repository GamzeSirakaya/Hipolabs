package com.example.hipo_intern.view.Fragments

import Member
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hipo_intern.FileService.Database
import com.example.hipo_intern.R
import com.example.hipo_intern.adapter.MemberAdapter
import com.example.hipo_intern.viewModel.ListMemberViewModel
import kotlinx.android.synthetic.main.fragment_list_member.*

class ListMemberFragment : Fragment() {
    private var situation = 0
    private lateinit var viewModel: ListMemberViewModel
    private val recyclerAdapter = MemberAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListMemberViewModel::class.java)
        if(situation == 0 && viewModel.memberLiveData.value==null) {
            viewModel.refresh()
        }
        situation = 1

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        observeLiveData()
        addMemberButton.setOnClickListener {
            viewModel.memberData()
        }
        search()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_member, container, false)
    }
    fun setupRecycler(){
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=recyclerAdapter

    }
    fun search(){
        searchTextView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               recyclerAdapter.update(viewModel.filterList(s.toString()))
            }
        })

    }
    private fun observeLiveData() {
        viewModel.memberLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                recyclerAdapter.update(it)
                //memberCount.text = viewModel.memberLiveData.value?.size.toString()+" members"
                if(!searchTextView.text.toString().equals("")) {

                    //If there is any change, update the member list
                    recyclerAdapter.update(viewModel.filterList(searchTextView.text.toString()))
                }
            }
        })
    }


}