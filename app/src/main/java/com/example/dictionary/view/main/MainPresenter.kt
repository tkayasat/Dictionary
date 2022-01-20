package com.example.dictionary.view.main

import android.content.Context
import android.view.View
import com.example.dictionary.datasource.DataSourceRemote
import com.example.dictionary.model.data.AppState
import com.example.dictionary.model.repository.RepositoryImpl
import com.example.dictionary.model.scheduler.MySchedulers
import com.example.dictionary.model.scheduler.MySchedulersImpl
import com.example.dictionary.presenter.Presenter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver

abstract class MainPresenter<T : AppState, V : View>(
    private val context: Context,
    private val interactor: MainInteractor = MainInteractor(
        context,
        RepositoryImpl(DataSourceRemote())
    ),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val schedulers: MySchedulers = MySchedulersImpl()
) : Presenter<T, V>, com.example.dictionary.view.common.View {
    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable += interactor
            .getData(word, isOnline)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
            .subscribeWith(getObserver())
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}

private fun View?.renderData(loading: AppState.Loading) {

}
