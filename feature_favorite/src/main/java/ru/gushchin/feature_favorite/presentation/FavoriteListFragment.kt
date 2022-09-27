package ru.gushchin.feature_favorite.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.gushchin.feature_favorite.databinding.FragmentFavoriteListBinding
import ru.gushchin.feature_favorite.di.FeatureFavoriteComponent
import javax.inject.Inject

class FavoriteListFragment : Fragment() {

    private var _binding: FragmentFavoriteListBinding? = null
    private val binding get() = _binding!!

    private val favoriteAdapter = FavoriteWeatherAdapter()

    @Inject
    lateinit var viewmodel: FavoriteListViewModel

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
        FeatureFavoriteComponent.featureFavoriteComponent?.inject(this)
        viewmodel.getFavoriteCityList()
        fetchPraySchedules()
        binding.favoriteRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = favoriteAdapter
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

    fun onLoaded(itemState: List<SillyWeather?>) {
        binding.progressBar.visibility = View.GONE
        favoriteAdapter.apply {
                submitData(itemState)
                setOnFavoriteSwitchClickListener = { item ->
                    // TODO: delete from favourite cities in db
                }
                setOnItemClickListener = {
                    Toast.makeText(context, "you clicked", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun showError(stateError: FavoriteListUiState) {
        Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show()
    }

    fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
    }
}