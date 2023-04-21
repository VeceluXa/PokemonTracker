package com.danilovfa.pokemontracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danilovfa.pokemontracker.data.local.dao.PokemonPageDao
import com.danilovfa.pokemontracker.data.local.model.PokemonItemEntity

@Database(
    entities = [PokemonItemEntity::class],
    version = 1
)
abstract class PokemonPageDatabase : RoomDatabase() {
    abstract val pokemonPageDao: PokemonPageDao
}