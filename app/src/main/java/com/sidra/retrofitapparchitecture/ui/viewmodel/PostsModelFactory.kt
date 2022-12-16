package com.sidra.retrofitapparchitecture.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sidra.retrofitapparchitecture.data.repository.PostsRepository
import javax.inject.Inject

class PostsModelFactory @Inject constructor(private val repository: PostsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostsViewModel(repository)as T
    }

}