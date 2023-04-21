package com.danilovfa.pokemontracker.data.local

import android.graphics.Bitmap
import com.danilovfa.pokemontracker.domain.model.PokemonDetails

class PokemonDetailsCache {
    /**
     * Gets an element from the cache
     * @param id The id of pokemon
     * @return Pokemon details
     */
    fun get(id: Int) : PokemonDetails {
        // TODO Add implementation
        return PokemonDetails(
            name = "bulbasaur",
            sprite = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888),
            types = listOf("grass"),
            weightInKg = 15.0,
            heightInCm = 50.0
        )
    }

    /**
     * Puts an element into cache.
     * @param pokemon Element to insert in the cache
     */
    fun put(pokemon: PokemonDetails) {

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