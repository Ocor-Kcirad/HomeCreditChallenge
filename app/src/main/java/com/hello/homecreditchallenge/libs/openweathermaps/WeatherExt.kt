package com.hello.homecreditchallenge.libs.openweathermaps

import android.net.Uri
import com.hello.homecreditchallenge.model.WeatherData

fun WeatherData.Weather.getIconUri() = Uri.parse("http://openweathermap.org/img/w/$icon.png")
