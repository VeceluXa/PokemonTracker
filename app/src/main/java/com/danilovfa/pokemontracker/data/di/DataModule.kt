package com.danilovfa.pokemontracker.data.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.danilovfa.pokemontracker.data.local.dao.PokemonDetailsDao
import com.danilovfa.pokemontracker.data.local.dao.PokemonPageDao
import com.danilovfa.pokemontracker.data.local.PokemonDatabase
import com.danilovfa.pokemontracker.data.local.model.PokemonItemEntity
import com.danilovfa.pokemontracker.data.remote.PokemonRemoteMediator
import com.danilovfa.pokemontracker.data.remote.PokemonDetailsAPI
import com.danilovfa.pokemontracker.data.remote.PokemonPageAPI
import com.danilovfa.pokemontracker.data.repository.PokemonDetailsRepository
import com.danilovfa.pokemontracker.data.repository.PokemonPageRepository
import com.danilovfa.pokemontracker.domain.repository.IPokemonDetailsRepository
import com.danilovfa.pokemontracker.domain.repository.IPokemonPageRepository
import com.danilovfa.pokemontracker.utils.Constants.Companion.BASE_URL
import com.danilovfa.pokemontracker.utils.Constants.Companion.PAGE_SIZE
import com.danilovfa.pokemontracker.utils.Constants.Companion.POKEMON_SIZE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providePokemonDetailsRepository(
        detailsAPI: PokemonDetailsAPI,
        dao: PokemonDetailsDao,
        @ApplicationContext appContext: Context): IPokemonDetailsRepository {
        return PokemonDetailsRepository(detailsAPI, dao, appContext)
    }

    @Provides
    @Singleton
    fun providePokemonPageRepository(
        pagerOnline: Pager<Int, PokemonItemEntity>,
        pagerOffline: Pager<Int, PokemonItemEntity>,
        @ApplicationContext context: Context
    ): IPokemonPageRepository {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return PokemonPageRepository(pagerOnline, pagerOffline, connectivityManager)
    }


    @Provides
    @Singleton
    fun providePokemonDetailsDatabase(app: Application) : PokemonDatabase {
        return Room.databaseBuilder(
            app,
            PokemonDatabase::class.java,
            PokemonDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePokemonDetailsDao(db: PokemonDatabase) : PokemonDetailsDao {
        return db.pokemonDetailsDao
    }

    @Provides
    @Singleton
    fun providePokemonPageDao(db: PokemonDatabase) : PokemonPageDao {
        return db.pokemonPageDao
    }

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providePokemonDetailsAPI(retrofit: Retrofit): PokemonDetailsAPI = retrofit.create(PokemonDetailsAPI::class.java)

    @Provides
    @Singleton
    fun providePokemonPageAPI(retrofit: Retrofit): PokemonPageAPI = retrofit.create(PokemonPageAPI::class.java)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PagerOnline
    @Provides
    @Singleton
    @PagerOnline
    fun providePokemonPagerOnline(dao: PokemonPageDao, api: PokemonPageAPI): Pager<Int, PokemonItemEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE*2,
                maxSize = POKEMON_SIZE
            ),
            remoteMediator = PokemonRemoteMediator(
                pokemonPageAPI = api,
                pokemonPageDao = dao
            )
        ) {
            dao.pagingSourceOnline()
        }
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PagerOffline
    @Provides
    @Singleton
    @PagerOffline
    fun providePokemonPagerOffline(dao: PokemonPageDao, api: PokemonPageAPI): Pager<Int, PokemonItemEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
            )
        ) {
            dao.pagingSourceOffline()
        }
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}