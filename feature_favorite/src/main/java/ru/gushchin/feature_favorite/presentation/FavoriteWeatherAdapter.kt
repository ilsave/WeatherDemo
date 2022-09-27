package ru.gushchin.feature_favorite.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.gushchin.feature_favorite.R
import ru.gushchin.feature_favorite.databinding.FavoriteWeatherItemBinding
import ru.gushchin.feature_favorite.di.FeatureFavoriteComponent

class FavoriteWeatherAdapter : RecyclerView.Adapter<FavoriteWeatherAdapter.WeatherViewHolder>() {

    private var weatherList = mutableListOf<SillyWeather?>()

    fun submitData(list: List<SillyWeather?>) {
        weatherList.clear()
        weatherList.addAll(list)
        notifyDataSetChanged()
    }

    var setOnItemClickListener: ((SillyWeather) -> Unit)? = null
    var setOnFavoriteSwitchClickListener: ((SillyWeather) -> Unit)? = null

    inner class WeatherViewHolder(val binding: FavoriteWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = FavoriteWeatherItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentItem = weatherList[position]
        with(holder.binding) {
            root.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.animation)
            cityNameTextView.text = currentItem?.name
            temperatureTextView.text = currentItem?.temp
            weatherDescriptionTextView.text = currentItem?.description
            Glide.with(holder.itemView.context).load(currentItem?.image).into(weatherImageView)
            favoriteImageView.setOnClickListener {
                setOnFavoriteSwitchClickListener?.invoke(currentItem!!)
            }
            root.setOnClickListener { setOnItemClickListener?.invoke(currentItem!!) }
        }
    }

    override fun getItemCount(): Int = weatherList.size
}