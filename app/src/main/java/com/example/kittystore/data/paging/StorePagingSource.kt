package com.example.kittystore.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kittystore.data.dto.StoreItemDto
import com.example.kittystore.domain.model.StoreItem

class StorePagingSource : PagingSource<Int, StoreItemDto>() {

    companion object {
        const val PAGE_SIZE = 20
        const val MAX_SIZE = 200
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoreItemDto> {

        val startId = params.key ?: 1

        val items = List(PAGE_SIZE) { index ->
            val id = startId + index
            StoreItemDto(
                id = id,
                name = "Cat #$id",
                imageUrl = "https://cataas.com/cat?${System.currentTimeMillis()}$id"
            )
        }

        return LoadResult.Page(
            data = items,
            prevKey = null,
            nextKey = startId + PAGE_SIZE
        )
    }

    override fun getRefreshKey(state: PagingState<Int, StoreItemDto>): Int? = null
}