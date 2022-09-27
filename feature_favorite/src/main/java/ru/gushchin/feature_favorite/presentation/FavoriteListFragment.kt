package ru.gushchin.feature_favorite.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.gushchin.feature_favorite.databinding.FragmentFavoriteListBinding
import ru.gushchin.feature_favorite.domain.Weather

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

    // INJECT
    private val favoriteAdapter = FavoriteWeatherAdapter()
    private val viewmodel = FavoriteListViewModel()

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

    private fun fetchPraySchedules() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.uiState.collect { state ->
                    when (state) {
                        is FavoriteListUiState.Loaded -> onLoaded(state.itemState)
                        is FavoriteListUiState.Error -> showError(state)
                        is FavoriteListUiState.Loading -> showLoading()
                    }
                }
            }
        }
    }

    fun onLoaded(itemState: Weather) {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(context, "got something", Toast.LENGTH_SHORT).show()
    }

    fun showError(stateError: FavoriteListUiState) {
        Toast.makeText(context,"something went wrong", Toast.LENGTH_SHORT).show()
    }

    fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        Toast.makeText(context,"loading", Toast.LENGTH_SHORT).show()
    }
}