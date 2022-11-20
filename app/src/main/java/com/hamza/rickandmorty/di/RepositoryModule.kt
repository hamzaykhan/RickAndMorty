package com.hamza.rickandmorty.di

import com.hamza.rickandmorty.data.repository.RMRepositoryImpl
import com.hamza.rickandmorty.domain.repository.RMRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRMRepository(
        rmRepositoryImpl: RMRepositoryImpl
    ): RMRepository
}