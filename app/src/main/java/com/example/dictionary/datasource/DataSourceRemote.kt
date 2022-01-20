package com.example.dictionary.datasource

import com.example.dictionary.model.data.DataModel
import javax.sql.DataSource

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation(),
) : DataSource<List<DataModel>> {

    fun getData(word: String) = remoteProvider.getData(word)

}