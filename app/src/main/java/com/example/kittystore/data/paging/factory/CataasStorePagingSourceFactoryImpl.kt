package com.example.kittystore.data.paging.factory

import androidx.paging.PagingSource
import com.example.kittystore.data.dto.StoreItemDto
import com.example.kittystore.data.paging.CataasStorePagingSource
import javax.inject.Inject

class CataasStorePagingSourceFactoryImpl @Inject constructor() : StorePagingSourceFactory {
    override fun create(): PagingSource<Int, StoreItemDto> {
        return CataasStorePagingSource()
    }
}