package com.example.kittystore.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.kittystore.data.mapper.toDomain
import com.example.kittystore.data.paging.factory.StorePagingSourceFactory
import com.example.kittystore.domain.model.StoreItem
import com.example.kittystore.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class StoreRepositoryImpl @Inject constructor(
    private val pagingSourceFactory: StorePagingSourceFactory
) : StoreRepository {

    override fun getStoreItems(): Flow<PagingData<StoreItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = StorePagingSourceFactory.PAGE_SIZE,
                initialLoadSize = StorePagingSourceFactory.PAGE_SIZE,
                prefetchDistance = StorePagingSourceFactory.PAGE_SIZE,
                maxSize = StorePagingSourceFactory.MAX_SIZE
            ),
            pagingSourceFactory = { pagingSourceFactory.create() }
        ).flow.map { pagingData ->
            pagingData.map { storeItemDto ->
                storeItemDto.toDomain()
            }
        }
    }
}