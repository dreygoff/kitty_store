package com.example.kittystore.di

import com.example.kittystore.data.paging.factory.CatApiStorePagingSourceFactoryImpl
import com.example.kittystore.data.paging.factory.CataasStorePagingSourceFactoryImpl
import com.example.kittystore.data.paging.factory.StorePagingSourceFactory
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
abstract class DataModule {

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
    abstract fun bindPasswordHasher(
        impl: PasswordHasherImpl
    ): PasswordHasher

    @Binds
    @Singleton
    abstract fun bindStoreRepository(
        impl: StoreRepositoryImpl
    ): StoreRepository

    @Binds
    @Singleton
    abstract fun bindStorePagingSourceFactory(
        impl: CatApiStorePagingSourceFactoryImpl
        //impl: CataasStorePagingSourceFactoryImpl
    ): StorePagingSourceFactory
}