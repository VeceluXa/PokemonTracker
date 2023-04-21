package com.danilovfa.pokemontracker.data.local.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danilovfa.pokemontracker.domain.model.PokemonDetails

@Entity(tableName = "details")
data class PokemonDetailsEntity(
    val name: String,
    val sprite: Bitmap,
    val types: List<String>,
    val weightInKg: Double,
    val heightInCm: Double,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    fun toDomain() : PokemonDetails {
        return PokemonDetails(
            name = name,
            sprite = sprite,
            types = types,
            weightInKg = weightInKg,
            heightInCm = heightInCm
        )
    }
}