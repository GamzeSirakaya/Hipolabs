package com.example.hipo_projects.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.hipo_projects.model.Position
import com.example.hipo_projects.network.Hipo_api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PositionViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val position = MutableLiveData<List<Position>>()
    var detailLoading = MutableLiveData<Boolean>()
    private val hipoApi = Hipo_api
    var detailError = MutableLiveData<Boolean>()
    fun refreshData(){
        getPosition()
    }

  fun getPosition(){
      compositeDisposable.add(
          hipoApi.getService().getPosition()
              .subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread()) .subscribeWith(object : DisposableSingleObserver<List<Position>>() {
                  override fun onSuccess(t: List<Position>) {
                      //Başarılı olursa
                      position.value=t


                  }

                  override fun onError(e: Throwable) {
                      //Hata alırsak
                      detailError.value = true
                      detailLoading.value = false
                      e.printStackTrace()
                  }
              })

      )
  }
}


