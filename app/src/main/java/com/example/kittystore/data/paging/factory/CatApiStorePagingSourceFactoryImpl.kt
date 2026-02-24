package com.example.kittystore.data.paging.factory

import androidx.paging.PagingSource
import com.example.kittystore.data.dto.StoreItemDto
import com.example.kittystore.data.paging.CatApiStorePagingSource
import com.example.kittystore.data.remote.CatApiService
import javax.inject.Inject

class CatApiStorePagingSourceFactoryImpl @Inject constructor(
    private val api: CatApiService
) : StorePagingSourceFactory {
    override fun create(): PagingSource<Int, StoreItemDto> {
        return CatApiStorePagingSource(api)
    }
}