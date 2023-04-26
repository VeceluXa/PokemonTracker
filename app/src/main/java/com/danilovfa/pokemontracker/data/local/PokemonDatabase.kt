package com.danilovfa.pokemontracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.danilovfa.pokemontracker.data.local.converters.BitmapTypeConverter
import com.danilovfa.pokemontracker.data.local.converters.StringTypeConverter
import com.danilovfa.pokemontracker.data.local.dao.PokemonDetailsDao
import com.danilovfa.pokemontracker.data.local.dao.PokemonPageDao
import com.danilovfa.pokemontracker.data.local.model.PokemonDetailsEntity
import com.danilovfa.pokemontracker.data.local.model.PokemonItemEntity

@Database(
    entities = [PokemonDetailsEntity::class, PokemonItemEntity::class],
    version = 1
)
@TypeConverters(StringTypeConverter::class, BitmapTypeConverter::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract val pokemonDetailsDao: PokemonDetailsDao
    abstract val pokemonPageDao: PokemonPageDao

    companion object {
        const val DATABASE_NAME = "pokemons"
    }
}