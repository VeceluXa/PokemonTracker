package com.danilovfa.pokemontracker.data.local

import com.danilovfa.pokemontracker.domain.model.PokemonItem

class PokemonsPageCache {
    /**
     * Gets an element from the cache
     * @param id The id of pokemon
     * @return Pokemon details
     */
    fun get() : List<PokemonItem> {
        // TODO Add implementation
        return listOf(PokemonItem("bulbasaur", 0))
    }

    /**
     * Puts an element into cache.
     * @param pokemon Element to insert in the cache
     */
    fun put(pokemon: PokemonItem) {

    }

    /**
     * Check if an element (Pokemon) exists in the cache
     * @param id The id of pokemon
     * @return true if the element is cached, otherwise false.
     */
    fun isCached(id: Int) : Boolean {
        return true
    }

    /**
     * Evict all elements of the cache.
     */
    fun evictAll() {

    }
}