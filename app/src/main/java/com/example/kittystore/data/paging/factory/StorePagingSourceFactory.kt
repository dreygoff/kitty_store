package com.example.kittystore.data.paging.factory

import androidx.paging.PagingSource
import com.example.kittystore.data.dto.StoreItemDto

interface StorePagingSourceFactory {
    fun create(): PagingSource<Int, StoreItemDto>

    companion object {
        const val PAGE_SIZE = 10
        const val MAX_SIZE = 200
    }
}