package com.example.dictionary.datasource

import com.example.dictionary.model.data.DataModel
import com.example.dictionary.remote.ApiService
import io.reactivex.rxjava3.core.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.sql.DataSource

class RetrofitImplementation: DataSource<List<DataModel>>  {
    companion object {
        private const val BASE_URL_LOCATIONS =
            "https://dictionary.skyeng.ru/api/public/v1/"
    }

    fun getData(word: String): Observable<List<DataModel>> =
        getService(BaseInterceptor.interceptor).search(word)

    private fun getService(interceptor: Interceptor): ApiService {
        return createRetrofit(interceptor).create(ApiService::class.java)
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        return httpClient.build()
    }

    private fun createRetrofit(interceptor: Interceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(createOkHttpClient(interceptor))
            .build()
    }
}
