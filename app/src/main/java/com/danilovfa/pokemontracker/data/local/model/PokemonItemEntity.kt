package com.danilovfa.pokemontracker.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danilovfa.pokemontracker.domain.model.PokemonItem

@Entity
data class PokemonItemEntity(
    val name: String,
    @PrimaryKey
    val id: Int
) {
    fun toDomain() : PokemonItem {
        return PokemonItem(name, id)
    }
}