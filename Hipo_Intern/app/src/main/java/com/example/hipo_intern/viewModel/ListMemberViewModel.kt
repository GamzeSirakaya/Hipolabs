package com.example.hipo_intern.viewModel

import Hipo
import Member
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.hipo_intern.FileService.Database
import kotlinx.coroutines.launch

class ListMemberViewModel(application: Application) : BaseViewModel(application) {
    val memberLiveData = MutableLiveData<List<Member>>()
    private val db = Database(application.applicationContext)

    fun refresh() {
        launch {
            memberLiveData.value = db.getData()
        }
    }

    fun memberData() {
        val member = Member("Gamze Sırakaya", 22, "Ankara", "GamzeSirakaya", Hipo("Intern", 0))
        var tempList = memberLiveData.value!!
        tempList = tempList.toCollection(arrayListOf())
        tempList.add(member)
        memberLiveData.value = tempList
        Toast.makeText(getApplication(), "new member", Toast.LENGTH_LONG).show()

    }

    fun giveData(): ArrayList<Member>? {
        return memberLiveData.value?.toCollection(ArrayList())
    }


    //To filter the recycler view
    fun filterList(s: String): ArrayList<Member> {
        var itemList = giveData()
        var tempList = ArrayList<Member>()
        for (d in itemList!!) {
            if (s.toLowerCase() in d.name.toString().toLowerCase()) {
                tempList.add(d)
            }
        }
        return tempList
    }
}