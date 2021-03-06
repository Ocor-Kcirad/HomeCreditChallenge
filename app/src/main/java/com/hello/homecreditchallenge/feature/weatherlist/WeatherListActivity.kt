package com.hello.homecreditchallenge.feature.weatherlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.hello.homecreditchallenge.R
import kotlinx.android.synthetic.main.activity_weather_list.fab
import kotlinx.android.synthetic.main.activity_weather_list.toolbar

class WeatherListActivity : AppCompatActivity() {

  private lateinit var sharedViewModel: WeatherListViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_weather_list)
    setSupportActionBar(toolbar)

    sharedViewModel = ViewModelProviders
        .of(this)
        .get(WeatherListViewModel::class.java)

    fab.setOnClickListener { sharedViewModel.loadCitiesWeatherData() }
  }

}
