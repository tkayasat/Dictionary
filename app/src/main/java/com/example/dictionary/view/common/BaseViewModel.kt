package com.example.dictionary.view.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dictionary.model.data.AppState
import com.example.dictionary.model.rx.MySchedulers
import com.example.dictionary.model.rx.MySchedulersImpl
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulers: MySchedulers = MySchedulersImpl(),
    application: Application

) : AndroidViewModel(application) {
    protected val context by lazy { getApplication<Application>().applicationContext }

    open fun getData(word: String): LiveData<T> = liveDataForViewToObserve

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    abstract fun getData(word: String, isOnline: Boolean): LiveData<AppState>
}