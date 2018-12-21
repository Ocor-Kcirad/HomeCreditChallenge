package com.hello.homecreditchallenge.libs.rest

import com.hello.homecreditchallenge.BuildConfig
import com.hello.homecreditchallenge.model.WeatherData
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val ENDPOINT = "http://api.openweathermap.org"
    private const val METRIC_UNITS = "metric"

    private val logger =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    private val client = OkHttpClient.Builder()
        .apply { addInterceptor(logger) }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()

    private val service: ApiService by lazy {
        retrofit.create<ApiService>(ApiService::class.java)
    }

    fun getCitiesWeatherData(vararg cityId: String): Single<List<WeatherData>> = service
        .getCitiesWeatherData(cityId.joinToString(separator = ","), METRIC_UNITS, BuildConfig.OPEN_WEATHER_APP_ID)
        .map { it.weatherList }

    fun getCityWeatherData(cityId: String): Single<WeatherData> = service
        .getCityWeatherData(cityId, METRIC_UNITS, BuildConfig.OPEN_WEATHER_APP_ID)
}