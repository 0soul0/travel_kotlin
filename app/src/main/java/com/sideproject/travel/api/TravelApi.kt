package com.sideproject.travel.api

import com.sideproject.travel.model.View
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TravelApi {
    @GET("{language}/Attractions/All")
    suspend fun queryViews(@Path("language") language: String,@Query("page") page:String): Response<View>

}