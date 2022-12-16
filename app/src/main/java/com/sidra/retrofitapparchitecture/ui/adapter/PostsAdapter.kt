package com.sidra.retrofitapparchitecture.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sidra.retrofitapparchitecture.data.model.Posts
import com.sidra.retrofitapparchitecture.data.model.PostsItem
import com.sidra.retrofitapparchitecture.databinding.PostsItemBinding
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PostsAdapter @Inject constructor(private val context: Context) : RecyclerView.Adapter<PostsAdapter.MyViewHolder>() {

     var postsList = ArrayList<PostsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = PostsItemBinding.inflate(inflater,parent,false)
        val viewObj = MyViewHolder(binding)
        return viewObj
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val posts = postsList[position]
        holder.binding.dataclass=posts
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    fun updateList(newList : Posts){

        postsList.clear()
        postsList.addAll(newList)
        notifyDataSetChanged()

    }

    class MyViewHolder(val binding: PostsItemBinding):RecyclerView.ViewHolder(binding.root){

    }

}