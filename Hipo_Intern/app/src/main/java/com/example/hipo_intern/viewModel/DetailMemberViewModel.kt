package com.example.hipo_intern.viewModel

import Member
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder

class DetailMemberViewModel(application: Application):BaseViewModel(application) {
    val memberLiveData = MutableLiveData<Member>()
    private val gson = GsonBuilder().create()

    fun setData(info: String) {
        var theMember = gson.fromJson(info, Member::class.java)
        memberLiveData.value = theMember
    }
}