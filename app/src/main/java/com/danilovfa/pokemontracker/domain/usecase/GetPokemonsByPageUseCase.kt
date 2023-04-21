package com.danilovfa.pokemontracker.domain.usecase

import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonsPageRepository

class GetPokemonsByPageUseCase(private val pokemonsPageRepository: IPokemonsPageRepository) {
    fun execute() : List<PokemonItem> {
        // TODO Add implementation
        // TODO Get local page. Check size. Get data from api after last item in local
        return pokemonsPageRepository.getPageCached()
    }
}