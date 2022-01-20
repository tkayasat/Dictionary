package com.example.dictionary.view.common

import com.example.dictionary.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}