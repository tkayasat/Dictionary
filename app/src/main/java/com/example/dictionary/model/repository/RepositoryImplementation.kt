package com.example.dictionary.model.repository

import com.example.dictionary.model.Repository
import com.example.dictionary.model.data.DataModel
import io.reactivex.rxjava3.core.Observable
import javax.sql.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> = dataSource.getData(word)
}