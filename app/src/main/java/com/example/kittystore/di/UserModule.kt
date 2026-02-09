package com.example.kittystore.di

import com.example.kittystore.data.repository.FakeUserRepositoryImpl
import com.example.kittystore.data.repository.UserRepository
import com.example.kittystore.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository = FakeUserRepositoryImpl()
}