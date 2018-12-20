package com.hello.homecreditchallenge.libs.rest.response

import com.google.gson.annotations.SerializedName
import com.hello.homecreditchallenge.model.WeatherData

data class WeatherDataListEnvelope(
    @SerializedName("cnt") val count: Int,
    @SerializedName("list") val weatherList: List<WeatherData>
)