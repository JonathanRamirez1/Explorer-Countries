package com.jonathan.explorercountries.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.jonathan.explorercountries.R
import com.jonathan.explorercountries.databinding.FragmentDetailCountryBinding

class DetailCountryFragment : Fragment() {

    private lateinit var binding: FragmentDetailCountryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDetailCountry()
    }

    private fun getDetailCountry() {
        arguments?.let { bundle ->
            binding.apply {
                textViewCountryName.text = bundle.getString("countryName")
                textViewCapital.text = bundle.getString("capital")
                textViewPopulation.text = bundle.getLong("population").toString()
                textViewArea.text = bundle.getDouble("area").toString()
                textViewLanguages.text = bundle.getString("languages")
                textViewCurrencies.text = bundle.getString("currencies")
                textViewBorderCountries.text = bundle.getString("borders")

                imageViewFlag.load(bundle.getString("flag")) {
                    placeholder(R.drawable.ic_access_time)
                    error(R.drawable.ic_load_error)
                    fallback(R.drawable.ic_fallback)
                }
            }
        }
    }
}
