package com.hello.homecreditchallenge.libs.rest

import com.hello.homecreditchallenge.libs.rest.response.WeatherDataListEnvelope
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/data/2.5/group")
    fun getWeatherData(
        @Query("id") ids: String,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Single<WeatherDataListEnvelope>

}