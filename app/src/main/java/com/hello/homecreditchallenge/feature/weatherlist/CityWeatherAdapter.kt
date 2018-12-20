package com.hello.homecreditchallenge.feature.weatherlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hello.homecreditchallenge.R
import com.hello.homecreditchallenge.libs.openweathermaps.getIconUri
import com.hello.homecreditchallenge.model.WeatherData
import kotlinx.android.synthetic.main.item_city_weather.view.*

class CityWeatherAdapter(private val callback: Delegate) : RecyclerView.Adapter<CityWeatherAdapter.ViewHolder>() {

    private val items = mutableListOf<WeatherData>()

    interface Delegate {
        fun onClickWeatherData(data: WeatherData)
    }

    fun setItems(newItems: List<WeatherData>) {
        val result = DiffUtil.calculateDiff(DiffCallback(items, newItems))
        items.clear()
        items.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int) = ViewHolder.create(viewGroup, callback)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) = viewHolder.bind(items[position])

    class DiffCallback(private val oldList: List<WeatherData>, private val newList: List<WeatherData>) :
        DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

    }

    class ViewHolder(itemView: View, private val callback: Delegate) : RecyclerView.ViewHolder(itemView) {

        private var data: WeatherData? = null

        companion object {
            fun create(parent: ViewGroup, callback: Delegate): ViewHolder = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_city_weather, parent, false)
                .let { ViewHolder(it, callback) }
        }

        init {
            itemView.setOnClickListener {
                data?.let { callback.onClickWeatherData(it) }
            }
        }

        fun bind(data: WeatherData) {
            this.data = data

            val temperature = itemView.context.getString(R.string.label_temperature_placeholder, data.main.currentTemp)
            val location = data.name + ", " + data.sys.country
            val weatherIcon = data.weathers[0].getIconUri() ?: ""

            itemView.locationTextView.text = location
            itemView.temperatureTextView.text = temperature
            Glide.with(itemView).load(weatherIcon).into(itemView.weatherIconImageView)
        }
    }
}