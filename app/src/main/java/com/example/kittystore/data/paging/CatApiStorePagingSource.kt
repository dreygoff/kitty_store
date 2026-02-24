package com.example.kittystore.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.example.kittystore.data.dto.StoreItemDto
import com.example.kittystore.data.paging.factory.StorePagingSourceFactory
import com.example.kittystore.data.remote.CatApiService
import okio.IOException
import javax.inject.Inject

class CatApiStorePagingSource @Inject constructor(
    private val api: CatApiService
) : PagingSource<Int, StoreItemDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoreItemDto> {
        val page = params.key ?: 0

        return try {
            val response = api.getCats(limit = StorePagingSourceFactory.PAGE_SIZE, page = page)

            val items = response.mapIndexed { index, cat ->
                StoreItemDto(
                    id = page * StorePagingSourceFactory.PAGE_SIZE + index + 1,
                    name = "CatApi Cat #${page * StorePagingSourceFactory.PAGE_SIZE + index + 1}",
                    imageUrl = cat.url
                )
            }

            LoadResult.Page(
                data = items,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (items.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, StoreItemDto>) = null
}