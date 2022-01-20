package com.example.dictionary.model.data

import com.example.dictionary.presenter.DataPresenterRU


sealed class AppState{
    data class Success(val data: List<DataPresenterRU>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}