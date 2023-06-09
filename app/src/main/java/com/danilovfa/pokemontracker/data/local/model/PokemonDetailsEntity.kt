package com.danilovfa.pokemontracker.data.local.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details")
data class PokemonDetailsEntity(
    val name: String,
    val sprite: Bitmap,
    val types: List<String>,
    val weightInHg: Int,
    val heightInDm: Int,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "details_id")
    val id: Int
)