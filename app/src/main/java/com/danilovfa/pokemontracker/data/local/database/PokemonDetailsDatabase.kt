package com.danilovfa.pokemontracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.danilovfa.pokemontracker.data.local.converters.BitmapTypeConverter
import com.danilovfa.pokemontracker.data.local.converters.StringTypeConverter
import com.danilovfa.pokemontracker.data.local.dao.PokemonDetailsDao
import com.danilovfa.pokemontracker.data.local.model.PokemonDetailsEntity

@Database(
    entities = [PokemonDetailsEntity::class],
    version = 2
)
@TypeConverters(StringTypeConverter::class, BitmapTypeConverter::class)
abstract class PokemonDetailsDatabase : RoomDatabase() {
    abstract val pokemonDetailsDao: PokemonDetailsDao

    companion object {
        const val DATABASE_NAME = "details"
    }
}