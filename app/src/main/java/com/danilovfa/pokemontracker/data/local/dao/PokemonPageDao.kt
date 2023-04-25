package com.danilovfa.pokemontracker.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.danilovfa.pokemontracker.data.local.model.PokemonItemEntity

@Dao
interface PokemonPageDao {
    @Query("SELECT * FROM pokemons")
    fun pagingSource(): PagingSource<Int, PokemonItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(itemEntity: PokemonItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<PokemonItemEntity>)
}