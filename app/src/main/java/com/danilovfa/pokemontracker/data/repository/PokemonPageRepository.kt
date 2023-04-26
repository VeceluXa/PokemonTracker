package com.danilovfa.pokemontracker.data.repository

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.danilovfa.pokemontracker.data.di.DataModule
import com.danilovfa.pokemontracker.data.local.model.PokemonItemEntity
import com.danilovfa.pokemontracker.data.mapper.PokemonItemEntityMapper
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonPageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonPageRepository @Inject constructor(
    @DataModule.PagerOnline
    private val pagerOnline: Pager<Int, PokemonItemEntity>,
    @DataModule.PagerOffline
    private val pagerOffline: Pager<Int, PokemonItemEntity>,
    private val cm: ConnectivityManager?
) : IPokemonPageRepository {
    val mapper = PokemonItemEntityMapper()

    override fun getPage(): Flow<PagingData<PokemonItem>> {
        Log.d("Pager", "getPage: ${isConnected()}")
        return if (isConnected())
            pagerOnline.flow.map { pagingData ->
                pagingData.map { pokemonItemEntity ->
                    mapper.mapFromEntity(pokemonItemEntity)
                }
            }
        else
            pagerOffline.flow.map { pagingData ->
                pagingData.map { pokemonItemEntity ->
                    mapper.mapFromEntity(pokemonItemEntity)
                }
            }
    }

    private fun isConnected(): Boolean {
        var isConnected = false
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                isConnected = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
        return isConnected
    }

}