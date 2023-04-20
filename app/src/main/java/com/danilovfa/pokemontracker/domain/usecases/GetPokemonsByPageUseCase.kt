package com.danilovfa.pokemontracker.domain.usecases

import com.danilovfa.pokemontracker.domain.models.PokemonItem

class GetPokemonsByPageUseCase {
    fun execute() : List<PokemonItem> {
        //  TODO Add implementation
        return listOf(PokemonItem("bulbasaur", 0))
    }
}