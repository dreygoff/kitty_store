package com.example.kittystore.domain.repository

import androidx.paging.PagingData
import com.example.kittystore.domain.model.StoreItem
import kotlinx.coroutines.flow.Flow

interface StoreRepository {

    fun getStoreItems(): Flow<PagingData<StoreItem>>
}