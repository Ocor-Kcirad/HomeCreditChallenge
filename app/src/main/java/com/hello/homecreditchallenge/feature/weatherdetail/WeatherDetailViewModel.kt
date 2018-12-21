package com.hello.homecreditchallenge.feature.weatherdetail

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hello.homecreditchallenge.libs.arch.ActionLiveData
import com.hello.homecreditchallenge.libs.rest.ApiClient
import com.hello.homecreditchallenge.model.WeatherData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherDetailViewModel(private val cityId: String) : ViewModel() {

  val weather: LiveData<WeatherData> get() = weatherLiveData
  val errors: LiveData<Throwable> get() = errorsLiveData

  private val weatherLiveData = MutableLiveData<WeatherData>()
  private val errorsLiveData = ActionLiveData<Throwable>()
  private val disposables = CompositeDisposable()

  init {
    loadCityWeatherData()
  }

  fun loadCityWeatherData() {
    disposables.add(
        ApiClient.getCityWeatherData(cityId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { weatherLiveData.value = it },
                { errorsLiveData.value = it }
            ))
  }

  override fun onCleared() {
    super.onCleared()
    disposables.clear()
  }

  class ViewModelFactory(private val intent: Intent) :
      ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      if (modelClass.isAssignableFrom(WeatherDetailViewModel::class.java)) {
        val cityId = intent.getStringExtra(WeatherDetailActivity.KEY_CITY_ID)
        return WeatherDetailViewModel(cityId) as T
      } else {
        throw IllegalArgumentException("ViewModel Not Found")
      }
    }
  }
}