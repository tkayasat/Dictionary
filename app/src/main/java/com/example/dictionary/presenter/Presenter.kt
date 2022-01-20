package com.example.dictionary.presenter

import android.view.View
import com.example.dictionary.model.data.AppState

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}