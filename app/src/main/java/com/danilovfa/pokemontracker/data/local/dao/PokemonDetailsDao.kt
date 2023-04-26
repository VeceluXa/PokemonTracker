package com.danilovfa.pokemontracker.data.local.dao

import androidx.room.*
import com.danilovfa.pokemontracker.data.local.model.PokemonDetailsEntity

@Dao
interface PokemonDetailsDao {
    @Query("SELECT * FROM details WHERE details_id = :id")
    suspend fun getDetailsById(id: Int) : PokemonDetailsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(detailsEntity: PokemonDetailsEntity)

    @Query("SELECT EXISTS(SELECT * FROM details WHERE details_id = :id)")
    fun isDetailsExist(id: Int) : Boolean
}