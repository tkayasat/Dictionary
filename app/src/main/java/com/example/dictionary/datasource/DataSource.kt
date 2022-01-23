package com.example.dictionary.datasource

import com.example.dictionary.model.data.DataModel
import io.reactivex.rxjava3.core.Observable

abstract class DataSource<T> {
    abstract fun getData(word: String): Observable<List<DataModel>>
}