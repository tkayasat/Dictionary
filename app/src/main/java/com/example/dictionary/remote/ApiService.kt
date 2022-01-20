package com.example.dictionary.remote

import com.example.dictionary.model.data.DataModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("ru/{word}")
    fun search(
        @Path("word") wordToSearch: String
    ): Observable<List<DataModel>>
}