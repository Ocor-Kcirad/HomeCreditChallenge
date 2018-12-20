package com.hello.homecreditchallenge.model

import com.google.gson.annotations.SerializedName

data class WeatherData(
    val id: String,
    val name: String,
    @SerializedName("coord") val coordinate: Coordinate,
    @SerializedName("weather") val weathers: List<Weather>,
    val main: Main,
    val wind: Wind?,
    val clouds: Clouds?,
    @SerializedName("dt") val timeOfData: String,
    val visibility: String,
    val sys: Sys
) {

    data class Coordinate(
        val lon: String,
        val lat: String
    )

    data class Wind(
        val speed: String,
        val deg: String
    )

    data class Clouds(val all: String)
    data class Weather(
        val id: String,
        val main: String,
        val description: String,
        val icon: String
    )

    data class Sys(
        val type: String,
        val id: String,
        val message: String,
        val country: String,
        val sunrise: Long,
        val sunset: Long
    )

    data class Main(
        val pressure: String,
        val humidity: String,
        @SerializedName("temp") val currentTemp: String,
        @SerializedName("temp_min") val minTemp: String,
        @SerializedName("temp_max") val maxTemp: String
    )

}

