package com.sideproject.travel.hilt

import com.sideproject.travel.api.TravelApi
import com.sideproject.travel.repo.TravelRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Config.BaseURL)
//        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request()
                        .newBuilder()
                        .addHeader(
                            "accept",
                            "application/json"
                        )
                        .build()
                )
            }.build()
    }

    @Singleton
    @Provides
    fun provideTravelRepo(retrofit: Retrofit): TravelRepo =
        TravelRepo(retrofit.create(TravelApi::class.java))

    @Singleton
    @Provides
    fun provideSSS(): String =
        "SSSSSSS"

}