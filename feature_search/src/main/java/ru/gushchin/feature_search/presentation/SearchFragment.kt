package ru.gushchin.feature_search.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import ru.gushchin.feature_search.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    // NEED to inject
    val searchViewModel = SearchViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchSearchData()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(context, "query", Toast.LENGTH_SHORT).show()
                query?.let {
                    searchViewModel.getCityWeather(query)
                }
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchSearchData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.uiState.collect { state ->
                    when (state) {
                        is SearchCityUiState.WaitingForSearching -> waitingForSearching()
                        is SearchCityUiState.Loaded -> onLoaded(state.itemState)
                        is SearchCityUiState.Error -> showError(state)
                        is SearchCityUiState.Loading -> showLoading()
                    }
                }
            }
        }
    }

    private fun waitingForSearching() {
        // TODO  add image or animation
    }

    private fun onLoaded(itemState: SillySearchCity) {
        binding.progressBar.visibility = View.GONE
        // avoid concatenate
        binding.cityInfoTextView.text = "${itemState.name} ${itemState.temp}"
    }

    private fun showError(stateError: SearchCityUiState) {
        Toast.makeText(context,"something went wrong", Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }
}
