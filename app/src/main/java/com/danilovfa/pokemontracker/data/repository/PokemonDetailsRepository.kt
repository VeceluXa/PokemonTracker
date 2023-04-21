package com.danilovfa.pokemontracker.data.repository

import android.graphics.Bitmap
import com.danilovfa.pokemontracker.domain.model.PokemonDetails
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonDetailsRepository

class PokemonDetailsRepository : IPokemonDetailsRepository {
    override fun getDetailsApi(pokemonItem: PokemonItem): PokemonDetails {
        // TODO Add implementation
        return PokemonDetails(
            name = "bulbasaur",
            sprite = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888),
            types = listOf("grass"),
            weightInKg = 15.0,
            heightInCm = 50.0
        )
    }

    override fun getDetailsCached(pokemonItem: PokemonItem): PokemonDetails {
        // TODO Add implementation
        return PokemonDetails(
            name = "bulbasaur",
            sprite = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888),
            types = listOf("grass"),
            weightInKg = 15.0,
            heightInCm = 50.0
        )
    }
}