package com.example.hipo_projects.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hipo_projects.R
import com.example.hipo_projects.adapter.Adapter
import com.example.hipo_projects.viewModel.PositionViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*


class homeFragment : Fragment() {
    private lateinit var viewModel: PositionViewModel
    private val recyclerAdapter = Adapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PositionViewModel::class.java)
        viewModel.refreshData()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerAdapter
        observeLiveData()
    }

    fun observeLiveData() {

        viewModel.position.observe(viewLifecycleOwner, Observer { position ->
            position?.let {


                recyclerAdapter.update(position)

            }
        })

    }
}