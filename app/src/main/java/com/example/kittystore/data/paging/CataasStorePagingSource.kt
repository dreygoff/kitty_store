package com.example.kittystore.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kittystore.data.dto.StoreItemDto
import com.example.kittystore.data.paging.factory.StorePagingSourceFactory

class CataasStorePagingSource : PagingSource<Int, StoreItemDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoreItemDto> {

        val startId = params.key ?: 1

        val items = List(StorePagingSourceFactory.PAGE_SIZE) { index ->
            val id = startId + index
            StoreItemDto(
                id = id,
                name = "Cataas Cat #$id",
                imageUrl = "https://cataas.com/cat?${System.currentTimeMillis()}$id"
            )
        }

        return LoadResult.Page(
            data = items,
            prevKey = null,
            nextKey = startId + StorePagingSourceFactory.PAGE_SIZE
        )
    }

    override fun getRefreshKey(state: PagingState<Int, StoreItemDto>): Int? = null
}