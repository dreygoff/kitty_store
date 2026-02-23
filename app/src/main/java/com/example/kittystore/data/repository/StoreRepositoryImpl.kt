package com.example.kittystore.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.kittystore.data.mapper.toDomain
import com.example.kittystore.data.paging.StorePagingSource
import com.example.kittystore.domain.model.StoreItem
import com.example.kittystore.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class StoreRepositoryImpl @Inject constructor() : StoreRepository {

    override fun getStoreItems(): Flow<PagingData<StoreItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = StorePagingSource.PAGE_SIZE,
                initialLoadSize = StorePagingSource.PAGE_SIZE,
                maxSize = StorePagingSource.MAX_SIZE
            ),
            pagingSourceFactory = { StorePagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { storeItemDto ->
                storeItemDto.toDomain()
            }
        }
    }
}