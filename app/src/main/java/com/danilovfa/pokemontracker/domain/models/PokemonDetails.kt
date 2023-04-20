package com.danilovfa.pokemontracker.domain.models

import android.graphics.Bitmap

data class PokemonDetails(
    val name: String,
    val sprite: Bitmap,
    val types: List<String>,
    val weightInKg: Double,
    val heightInCm: Double
)