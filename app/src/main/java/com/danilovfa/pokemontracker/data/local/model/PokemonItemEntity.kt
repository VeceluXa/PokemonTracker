package com.danilovfa.pokemontracker.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danilovfa.pokemontracker.domain.model.PokemonItem

@Entity(tableName = "pokemons")
data class PokemonItemEntity(
    val name: String = "",
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    fun toDomain() : PokemonItem {
        return PokemonItem(name, id)
    }
}