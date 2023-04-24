package com.danilovfa.pokemontracker.data.remote.response

data class PokemonPage(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)
