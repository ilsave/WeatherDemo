package ru.gushchin.feature_favorite.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gushchin.feature_favorite.databinding.FragmentFavoriteListBinding

class FavoriteListFragment : Fragment() {

    private val dummyList = arrayListOf(
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/04d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/01d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/13d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/04d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/01d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/13d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/04d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/01d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/13d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/04d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/01d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/13d@2x.png", description = "clear Sky"),
        SillyWeather(name = "Balachna", temp = "55", wind = "Wind is strong", image = "https://openweathermap.org/img/wn/03d@2x.png", description = "clear Sky"),
    )

        private var _binding: FragmentFavoriteListBinding? = null
    private val binding get() = _binding!!
    private val favoriteAdapter = FavoriteWeatherAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.favoriteRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            favoriteAdapter.apply {
                submitData(dummyList)
                setOnFavoriteSwitchClickListener = { item ->
                    dummyList.remove(item)
                    submitData(dummyList)
                }
                setOnItemClickListener = {
                    Toast.makeText(context, "you clicked", Toast.LENGTH_SHORT).show()
                }
            }.also {
                adapter = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}