package com.sideproject.travel.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sideproject.travel.api.TravelService
import com.sideproject.travel.model.Data
import kotlinx.coroutines.flow.Flow

class TravelRepo(private val service: TravelService) {


    fun queryViews(): Flow<PagingData<Data>> {

        var factory = { PagingSource(service) }
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = factory
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}