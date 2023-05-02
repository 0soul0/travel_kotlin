package com.sideproject.travel.repo

import com.sideproject.travel.api.TravelApi
import com.sideproject.travel.model.Data

class TravelRepo(private val travelApi: TravelApi) {


    suspend fun queryViews(language: String, page: String): List<Data> {
        val res = travelApi.queryViews(language, page)
        return res.body()?.data ?: emptyList()
    }
}