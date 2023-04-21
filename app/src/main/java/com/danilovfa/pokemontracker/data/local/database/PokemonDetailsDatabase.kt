package com.danilovfa.pokemontracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danilovfa.pokemontracker.data.local.dao.PokemonDetailsDao
import com.danilovfa.pokemontracker.data.local.model.PokemonDetailsEntity

@Database(
    entities = [PokemonDetailsEntity::class],
    version = 1
)
abstract class PokemonDetailsDatabase : RoomDatabase() {
    abstract val pokemonDetailsDao: PokemonDetailsDao
}