package com.example.hipo_projects.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hipo_projects.model.Position
import com.example.hipo_projects.network.Hipo_api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PositionViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val position = MutableLiveData<List<Position>>()
    var detailLoading = MutableLiveData<Boolean>()
    private val hipoApi = Hipo_api

    fun InternPosition() {
        compositeDisposable.add(
            hipoApi.getService().getPosition()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { detailLoading.value = true }
                .subscribe({ position ->
                    position
                    if (position != null) {
                        position
                        print(position)
                    }

                })
        )
    }
}