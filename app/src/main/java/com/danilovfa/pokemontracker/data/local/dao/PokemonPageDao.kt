package com.danilovfa.pokemontracker.data.local.dao

import androidx.room.*
import com.danilovfa.pokemontracker.data.local.model.PokemonItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonPageDao {
    @Query("SELECT * FROM pokemons")
    suspend fun getPage(): List<PokemonItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(itemEntity: PokemonItemEntity)
}