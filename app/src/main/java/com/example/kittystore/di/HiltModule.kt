package com.example.kittystore.di

import com.example.kittystore.data.repository.FakeUserRepositoryImpl
import com.example.kittystore.data.repository.StoreRepositoryImpl
import com.example.kittystore.data.security.PasswordHasherImpl
import com.example.kittystore.domain.repository.StoreRepository
import com.example.kittystore.domain.repository.UserRepository
import com.example.kittystore.domain.security.PasswordHasher
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        // For development and tests
        impl: FakeUserRepositoryImpl
        // For local user storage
        // impl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun providePasswordHasher(
        impl: PasswordHasherImpl
    ): PasswordHasher

    @Binds
    @Singleton
    abstract fun provideStoreRepository(
        impl: StoreRepositoryImpl
    ): StoreRepository
}