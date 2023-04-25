package com.danilovfa.pokemontracker.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.danilovfa.pokemontracker.databinding.PokemonItemLayoutBinding
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import by.kirich1409.viewbindingdelegate.viewBinding
import com.danilovfa.pokemontracker.R

class PokemonPageAdapter(context: Context) :
    PagingDataAdapter<PokemonItem, PokemonPageAdapter.PokemonViewHolder>(PokemonDiffItemCallback) {
    private val layoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(layoutInflater.inflate(R.layout.pokemon_item_layout, parent, false))
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding: PokemonItemLayoutBinding by viewBinding()
        fun bind(pokemonItem: PokemonItem?) {
            viewBinding.textPokemon.text = pokemonItem?.name ?: ""
        }
    }

    private object PokemonDiffItemCallback : DiffUtil.ItemCallback<PokemonItem>() {
        override fun areItemsTheSame(oldItem: PokemonItem, newItem: PokemonItem): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: PokemonItem, newItem: PokemonItem): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name
        }
    }
}