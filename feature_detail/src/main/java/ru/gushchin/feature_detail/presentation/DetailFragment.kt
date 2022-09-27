package ru.gushchin.feature_detail.presentation

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {

                }
            }
        }

        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        binding.navToFav.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://example.google.app/favorite_list_fragment".toUri())
                .build()
            findNavController().navigate(request)
        }
        lifecycleScope.launchWhenStarted {
            viewModel.getTheCurrent()
        }
        fetchPraySchedules()

        binding.navToSearch.setOnClickListener {
            Toast.makeText(context, "hehe", Toast.LENGTH_SHORT).show()


            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://example.google.app/search_fragment".toUri())
                .build()
            findNavController().navigate(request)
        }
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
        Toast.makeText(context, itemState.name, Toast.LENGTH_SHORT).show()
        binding.textView2.text = itemState.name
    }

    fun showError(stateError: DetailUiState) {
        Toast.makeText(context,"something went wrong", Toast.LENGTH_SHORT).show()
    }

    fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        Toast.makeText(context,"loading", Toast.LENGTH_SHORT).show()
    }
}