package com.jonathan.explorercountries.presentation.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jonathan.explorercountries.databinding.FragmentCountryBinding
import com.jonathan.explorercountries.presentation.ui.adapter.CountryAdapter
import com.jonathan.explorercountries.presentation.utils.Resource
import com.jonathan.explorercountries.presentation.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryFragment: Fragment() {

    private lateinit var binding: FragmentCountryBinding
    private lateinit var countryAdapter: CountryAdapter

    private val viewModel: CountryViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        setSearchQuery()
        refreshCountries()
    }

    private fun setupRecyclerView() {
        countryAdapter = CountryAdapter()
        binding.countriesRecyclerView.adapter = countryAdapter
    }

    private fun observeViewModel() {
        viewModel.countries.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    countryAdapter.submitList(resource.data)
                    binding.progressBar.visibility = View.GONE
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun setSearchQuery() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.setSearchQuery(s.toString())
            }
        })
    }

    private fun refreshCountries() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshCountries()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}
