package com.danilovfa.pokemontracker.domain.usecases

import com.danilovfa.pokemontracker.domain.models.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonsPageRepository

class GetPokemonsByPageUseCase(private val pokemonsPageRepository: IPokemonsPageRepository) {
    fun execute() : List<PokemonItem> {
        // TODO Add implementation
        // TODO Get local page. Check size. Get data from api after last item in local
        return pokemonsPageRepository.getPageCached()
    }
}