package com.sidra.retrofitapparchitecture.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sidra.retrofitapparchitecture.data.model.Posts
import com.sidra.retrofitapparchitecture.data.source.remote.PostsApiService
import javax.inject.Inject

class PostsRepository @Inject constructor(private val postsApiService: PostsApiService){
    private val postsMlv = MutableLiveData<Posts>()
    val postsLv : LiveData<Posts>
    get() = postsMlv

    val msg = MutableLiveData<String>()


    suspend fun getPosts(){
        val result = postsApiService.getPosts()
        if(result.body()!=null)
        {
            postsMlv.postValue(result.body())
        }
        else{
            msg.value="Couldn't retrieve data!"
        }
    }
}