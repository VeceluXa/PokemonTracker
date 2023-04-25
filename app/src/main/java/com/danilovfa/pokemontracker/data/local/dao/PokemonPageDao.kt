package com.danilovfa.pokemontracker.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.danilovfa.pokemontracker.data.local.model.PokemonItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonPageDao {
    @Query("SELECT * FROM pokemons")
    suspend fun getPage(): List<PokemonItemEntity>?

    @Query("SELECT * FROM pokemons LIMIT :pageSize OFFSET :offset")
    fun getItemsByPage(pageSize: Int, offset: Int): List<PokemonItemEntity>

    @Query("SELECT * FROM pokemons WHERE name LIKE :query")
    fun pagingSource(query: String): PagingSource<Int, PokemonItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(itemEntity: PokemonItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<PokemonItemEntity>)
}