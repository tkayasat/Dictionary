package com.example.dictionary.view.main

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.dictionary.datasource.DataSourceRemote
import com.example.dictionary.model.data.AppState
import com.example.dictionary.model.repository.RepositoryImplementation
import com.example.dictionary.view.common.BaseViewModel
import io.reactivex.rxjava3.observers.DisposableObserver

class MainViewModel(
    application: Application
) : BaseViewModel<AppState>(application = application) {
    private val interactor: MainInteractor = MainInteractor(
        context,
        RepositoryImplementation(DataSourceRemote())
    )
    private var appState: AppState? = null

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .doOnSubscribe { liveDataForViewToObserve.value = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )
        return super.getData(word)
    }

    fun getData(): LiveData<AppState> {
        liveDataForViewToObserve.value = appState
        return super.getData("")
    }


    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(t: AppState) {
                appState = t
                liveDataForViewToObserve.value = t
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }


            override fun onComplete() {
                //не требуется
            }
        }
    }
}
