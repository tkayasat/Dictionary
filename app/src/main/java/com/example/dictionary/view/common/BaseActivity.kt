package com.example.dictionary.view.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionary.model.data.AppState
import com.example.dictionary.presenter.Presenter

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    protected open lateinit var presenter: Presenter<T, View>

    protected abstract fun createPresenter(): Presenter<T, View>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView(this)
        super.onStop()
    }
}