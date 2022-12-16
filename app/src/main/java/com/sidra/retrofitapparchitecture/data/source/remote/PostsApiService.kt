package com.sidra.retrofitapparchitecture.data.source.remote

import com.sidra.retrofitapparchitecture.data.model.Posts
import retrofit2.Response
import retrofit2.http.GET

interface PostsApiService {

    @GET("posts")
    suspend fun getPosts(): Response<Posts>
}