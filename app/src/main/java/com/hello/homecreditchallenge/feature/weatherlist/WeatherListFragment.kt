package com.hello.homecreditchallenge.feature.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hello.homecreditchallenge.R
import com.hello.homecreditchallenge.model.WeatherData
import kotlinx.android.synthetic.main.fragment_weather_list.recyclerView
import org.jetbrains.anko.support.v4.toast

class WeatherListFragment : Fragment(), CityWeatherAdapter.Delegate {

    private lateinit var adapter: CityWeatherAdapter
    private lateinit var sharedViewModel: WeatherListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_weather_list, container, false)!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()

        sharedViewModel = ViewModelProviders
            .of(activity!!)
            .get(WeatherListViewModel::class.java)

      sharedViewModel
          .weathers
          .observe(this, Observer {
            toast("Success")
            adapter.setItems(it)
        })

      sharedViewModel
          .errors
          .observe(this, Observer { toast(it.message.toString()) })
    }

    override fun onClickWeatherData(data: WeatherData) {
        toast("Clicked")
    }

    private fun setupRecyclerView() {
        adapter = CityWeatherAdapter(this)
        recyclerView.adapter = adapter
    }
}
