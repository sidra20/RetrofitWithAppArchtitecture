package com.sidra.retrofitapparchitecture.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sidra.retrofitapparchitecture.data.model.Posts
import com.sidra.retrofitapparchitecture.data.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val repository: PostsRepository) :ViewModel() {

    val postsLiveData : LiveData<Posts>
    get() = repository.postsLv

    private val msg = MutableLiveData<String>()
    val msgLv : LiveData<String>
    get() = msg

    init {
        try {
            viewModelScope.launch (Dispatchers.IO){
                repository.getPosts()

            }
            msg.value="Data retieved successfully!"

        }
        catch (e:Exception)
        {
            Log.d("main",""+e.message)
            msg.value="Couldn't retrieve data!"

        }

    }

}