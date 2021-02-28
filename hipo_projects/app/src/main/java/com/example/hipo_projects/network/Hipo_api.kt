package com.example.hipo_projects.network

import com.example.hipo_projects.model.Position
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Hipo_api {
    @GET("internship-positions/")
    fun getPosition(): Single<List<Position>>

    companion object {
        const val BASE_URL = "https://hipolabs.com/api/"
        fun getService(): Hipo_api {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Hipo_api::class.java)

        }
    }
}
