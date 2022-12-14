package ru.gushchin.feature_detail.presentation

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import ru.gushchin.feature_detail.R
import ru.gushchin.feature_detail.data.models.Weather
import ru.gushchin.feature_detail.databinding.FragmentDetailBinding
import ru.gushchin.feature_detail.di.FeatureDetailComponent
import javax.inject.Inject

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: DetailViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        FeatureDetailComponent.featureDetailComponent?.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarHome.inflateMenu(R.menu.menu)
        // TODO move to string holder
        binding.toolbarHome.title = "WeatherApp"

        binding.toolbarHome.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_nav_search -> {
                    val request = NavDeepLinkRequest.Builder
                        .fromUri("android-app://example.google.app/search_fragment".toUri())
                        .build()
                    findNavController().navigate(request)
                }
            }
            true
        }

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    lifecycleScope.launchWhenStarted {
                        viewModel.getTheCurrent()
                    }
                }
            }
        }

        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        binding.favoritesButton.setOnClickListener {
            // TODO hardcoded uri, should be stored as a constant value
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://example.google.app/favorite_list_fragment".toUri())
                .build()
            findNavController().navigate(request)
        }

        binding.favoriteButton.setOnClickListener {

        }

        fetchPraySchedules()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchPraySchedules() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is DetailUiState.Loaded -> onLoaded(state.itemState)
                        is DetailUiState.Error -> showError(state)
                        is DetailUiState.Loading -> showLoading()
                    }
                }
            }
        }
    }

    fun onLoaded(itemState: Weather) {
        binding.progressBar.visibility = View.GONE
        binding.cityNameTextView.text = itemState.name
        binding.temperatureTextView.text = itemState.main?.temp.toString()
        binding.descriptionTextView.text = itemState.weather?.get(0)?.description
        Toast.makeText(context, itemState.name, Toast.LENGTH_SHORT).show()
    }

    fun showError(stateError: DetailUiState) {
        Toast.makeText(context,"something went wrong", Toast.LENGTH_SHORT).show()
    }

    fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        Toast.makeText(context,"loading", Toast.LENGTH_SHORT).show()
    }
}