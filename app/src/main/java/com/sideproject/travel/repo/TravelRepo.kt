package com.sideproject.travel.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sideproject.travel.api.TravelService
import com.sideproject.travel.model.Data
import kotlinx.coroutines.flow.Flow

class TravelRepo(private val service: TravelService) {


//    suspend fun queryViews(language: String, page: String): List<Data> {
//        val res = travelApi.queryViews(language, page)
//        return res.body()?.data ?: emptyList()
//    }


    fun queryViews(language: String): Flow<PagingData<Data>> {

        val pagingSourceFactory = { PagingSource(service, language) }
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}