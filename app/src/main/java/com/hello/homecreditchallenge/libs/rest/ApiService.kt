package com.hello.homecreditchallenge.libs.rest

import com.hello.homecreditchallenge.libs.rest.response.WeatherDataListEnvelope
import com.hello.homecreditchallenge.model.WeatherData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/data/2.5/group")
    fun getCitiesWeatherData(
        @Query("id") ids: String,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Single<WeatherDataListEnvelope>

    @GET("/data/2.5/weather")
    fun getCityWeatherData(
        @Query("id") id: String,
        @Query("appid") appId: String
    ): Single<WeatherData>
}