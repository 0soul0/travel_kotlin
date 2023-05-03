package com.sideproject.travel.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.sideproject.travel.api.TravelService
import com.sideproject.travel.hilt.Data.Companion.languages
import com.sideproject.travel.model.Data
import com.sideproject.travel.repo.TravelRepo.Companion.NETWORK_PAGE_SIZE
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 1
var language: String=languages[0].value
class PagingSource(
    private val service: TravelService,
) : PagingSource<Int, Data>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
        return try {
            val response = service.queryViews(language, position.toString())
            val repos = response.body()?.data
            val nextKey = if (repos!!.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = repos,
                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
