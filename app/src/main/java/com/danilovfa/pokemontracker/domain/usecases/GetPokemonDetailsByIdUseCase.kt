package com.danilovfa.pokemontracker.domain.usecases

import android.graphics.Bitmap
import android.graphics.Picture
import com.danilovfa.pokemontracker.domain.models.PokemonDetails
import com.danilovfa.pokemontracker.domain.models.PokemonItem

class GetPokemonDetailsByIdUseCase {
    fun execute(pokemonItem: PokemonItem) : PokemonDetails {
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