package com.hello.homecreditchallenge.feature.weatherlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hello.homecreditchallenge.libs.arch.ActionLiveData
import com.hello.homecreditchallenge.libs.rest.ApiClient
import com.hello.homecreditchallenge.model.WeatherData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherListViewModel : ViewModel() {

    companion object {
        private const val LONDON = 2643743
        private const val KEIV = 703448
        private const val MOSCOW = 524901
    }

    val weathers: LiveData<List<WeatherData>> get() = weatherListLiveData
    val errors: LiveData<Throwable> get() = errorsLiveData

    private val weatherListLiveData = MutableLiveData<List<WeatherData>>()
    private val errorsLiveData = ActionLiveData<Throwable>()
    private val disposables = CompositeDisposable()

    init {
        loadWeatherData()
    }

    fun loadWeatherData() {
        disposables.add(ApiClient.getWeatherData(MOSCOW, KEIV, LONDON)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { weatherListLiveData.value = it },
                { errorsLiveData.value = it }
            ))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}