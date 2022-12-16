package com.sidra.retrofitapparchitecture.di

import com.sidra.retrofitapparchitecture.data.source.remote.PostsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val BASE_URL="https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun providesPostRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesPostApiService(retrofit: Retrofit) : PostsApiService =
        retrofit.create(PostsApiService::class.java)

}