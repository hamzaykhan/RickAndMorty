package com.hamza.rickandmorty.di

import android.app.Application
import androidx.room.Room
import com.hamza.rickandmorty.data.local.RMDatabase
import com.hamza.rickandmorty.data.remote.RickAndMortyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRickAndMortyApi(): RickAndMortyApi {
        return Retrofit.Builder()
            .baseUrl(RickAndMortyApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRickAndMortyDatabase(app: Application): RMDatabase {
        return Room.databaseBuilder(
            app,
            RMDatabase::class.java,
            "RickAndMortyDB.db"
        ).build()
    }
}