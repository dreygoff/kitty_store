package com.example.kittystore.domain.usecase.store

import androidx.paging.PagingData
import com.example.kittystore.domain.model.StoreItem
import com.example.kittystore.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoreItemUseCase @Inject constructor(
    private val storeRepository: StoreRepository
) {

    operator fun invoke(): Flow<PagingData<StoreItem>> {
        return storeRepository.getStoreItems()
    }
}