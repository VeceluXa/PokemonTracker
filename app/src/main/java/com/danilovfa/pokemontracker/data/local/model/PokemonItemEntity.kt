package com.danilovfa.pokemontracker.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class PokemonItemEntity(
    val name: String = "",
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)