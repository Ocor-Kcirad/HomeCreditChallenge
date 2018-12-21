package com.hello.homecreditchallenge.feature.weatherdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.hello.homecreditchallenge.R
import com.hello.homecreditchallenge.feature.weatherdetail.WeatherDetailViewModel.ViewModelFactory
import com.hello.homecreditchallenge.libs.openweathermaps.getIconUri
import com.hello.homecreditchallenge.model.WeatherData
import kotlinx.android.synthetic.main.activity_weather_detail.fab
import kotlinx.android.synthetic.main.activity_weather_detail.toolbar
import kotlinx.android.synthetic.main.content_weather_detail.highestTemperatureTextView
import kotlinx.android.synthetic.main.content_weather_detail.humidityTextView
import kotlinx.android.synthetic.main.content_weather_detail.lowestTemperatureTextView
import kotlinx.android.synthetic.main.content_weather_detail.pressureTextView
import kotlinx.android.synthetic.main.content_weather_detail.temperatureTextView
import kotlinx.android.synthetic.main.content_weather_detail.weatherIconImageView
import kotlinx.android.synthetic.main.content_weather_detail.weatherNameTextView
import kotlinx.android.synthetic.main.content_weather_detail.windDegreeImageView
import kotlinx.android.synthetic.main.content_weather_detail.windSpeedTextView
import org.jetbrains.anko.toast

class WeatherDetailActivity : AppCompatActivity() {

  companion object {
    const val KEY_CITY_ID = "com.hello.homecreditchallenge.feature.weatherdetail.city-id"
  }

  private lateinit var viewModel: WeatherDetailViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_weather_detail)
    setupToolbar()

    viewModel = ViewModelProviders
        .of(this, ViewModelFactory(intent))
        .get(WeatherDetailViewModel::class.java)

    viewModel
        .weather
        .observe(this, Observer {
          toast("Loaded")
          updateWeather(it)
        })

    viewModel
        .errors
        .observe(this, Observer { it.message?.let { msg -> toast(msg) } })


    fab.setOnClickListener { viewModel.loadCityWeatherData() }
  }

  private fun setupToolbar() {
    setSupportActionBar(toolbar)
    toolbar.setNavigationOnClickListener { finish() }
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
  }

  private fun updateWeather(weather: WeatherData) {
    val temperature = getString(R.string.label_temperature_placeholder, weather.main.currentTemp)
    val location = weather.name + ", " + weather.sys.country
    val weatherIcon = weather.weathers[0].getIconUri() ?: ""
    val humidity = getString(R.string.label_humidity_placeholder, weather.main.humidity)
    val pressure = getString(R.string.label_pressure_placeholder, weather.main.pressure)
    val minTemp = getString(R.string.label_temperature_placeholder, weather.main.minTemp)
    val maxTemp = getString(R.string.label_temperature_placeholder, weather.main.maxTemp)
    val weatherName = weather.weathers.getOrNull(0)?.main ?: "None"
    val windSpeed = getString(R.string.label_windspeed_placeholder, weather.wind?.speed ?: "0")
    val windDegree = weather.wind?.deg?.toFloatOrNull() ?: 0f

    temperatureTextView.text = temperature
    humidityTextView.text = humidity
    lowestTemperatureTextView.text = minTemp
    highestTemperatureTextView.text = maxTemp
    pressureTextView.text = pressure
    weatherNameTextView.text = weatherName
    windSpeedTextView.text = windSpeed
    windDegreeImageView.rotation = windDegree

    Glide.with(this)
        .load(weatherIcon)
        .into(weatherIconImageView)
    toolbar.title = location
  }

}
