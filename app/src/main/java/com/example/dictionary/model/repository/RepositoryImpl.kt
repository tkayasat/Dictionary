package com.example.dictionary.model.repository

import com.example.dictionary.model.Repository
import com.example.dictionary.model.data.DataModel
import javax.sql.DataSource

class RepositoryImpl(private val dataSource: DataSource<List<DataModel>>) : Repository<List<DataModel>> {

    override fun getData(word: String) = dataSource.getData(word)


}