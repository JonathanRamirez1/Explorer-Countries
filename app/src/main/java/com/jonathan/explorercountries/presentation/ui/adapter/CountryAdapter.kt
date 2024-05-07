package com.jonathan.explorercountries.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jonathan.explorercountries.R
import com.jonathan.explorercountries.databinding.ItemCountryBinding
import com.jonathan.explorercountries.domain.model.Country

class CountryAdapter :
    ListAdapter<Country, CountryAdapter.CountryViewHolder>(CountryDiffCallback()) {

    private lateinit var navController: NavController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = getItem(position)
        holder.bind(country)
        holder.itemView.setOnClickListener { view ->
            navController = Navigation.findNavController(view)
            navController.navigate(
                R.id.detailCountryFragment, bundleOf(
                    "countryName" to country.name?.official,
                    "capital" to country.capital?.firstOrNull(),
                    "flag" to country.flags?.png,
                    "population" to country.population,
                    "area" to country.area,
                    "borders" to country.borders,
                    "languages" to country.languages,
                    "currencies" to country.currencies,
                    "region" to country.region,
                    "subregion" to country.subregion
                )
            )
        }
    }

    class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country) {
            binding.nameTextView.text = country.name?.official
            binding.capitalTextView.text = country.capital?.firstOrNull() ?: "No Capital"
            binding.flagImageView.load(country.flags?.png) {
                placeholder(R.drawable.ic_access_time)
                error(R.drawable.ic_load_error)
                fallback(R.drawable.ic_fallback)
            }
        }
    }

    class CountryDiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name?.official == newItem.name?.official
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
}
