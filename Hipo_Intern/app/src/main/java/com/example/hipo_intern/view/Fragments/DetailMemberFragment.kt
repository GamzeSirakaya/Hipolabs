package com.example.hipo_intern.view.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.hipo_intern.R
import com.example.hipo_intern.viewModel.DetailMemberViewModel
import kotlinx.android.synthetic.main.fragment_detail_member.*

class DetailMemberFragment : Fragment() {
    private lateinit var viewModel: DetailMemberViewModel
    private var member= ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_member, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            member = DetailMemberFragmentArgs.fromBundle(it).listMember
        }
        viewModel = ViewModelProviders.of(this).get(DetailMemberViewModel::class.java)
        viewModel.setData(member)
        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.memberLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                member_name_detail.text = it.name + ", " + it.age.toString()
                member_location_detail.text = it.location
                member_github_detail.text = it.github
                member_position_detail.text =
                    it.hipo.position + ", " + it.hipo.years_in_hipo.toString() + " years"
                action.setOnClickListener {
                    action(it)


                }
            }
        })
    }

    fun action(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_detailMemberFragment_to_listMemberFragment)
    }

}