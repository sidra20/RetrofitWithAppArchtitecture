package com.sidra.retrofitapparchitecture.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sidra.retrofitapparchitecture.R
import com.sidra.retrofitapparchitecture.data.repository.PostsRepository
import com.sidra.retrofitapparchitecture.databinding.FragmentPostsFrgamentBinding
import com.sidra.retrofitapparchitecture.ui.adapter.PostsAdapter
import com.sidra.retrofitapparchitecture.ui.viewmodel.PostsModelFactory
import com.sidra.retrofitapparchitecture.ui.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostsFrgament : Fragment() {

    private lateinit var binding: FragmentPostsFrgamentBinding

    lateinit var postsAdapter: PostsAdapter


    @Inject
    lateinit var factory : PostsModelFactory


    lateinit var viewModel: PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsFrgamentBinding.inflate(layoutInflater,container,false)

        viewModel=ViewModelProvider(this,factory).get(PostsViewModel::class.java)
        binding.recyclerPosts.layoutManager=LinearLayoutManager(context)
        postsAdapter=PostsAdapter(requireContext())
        binding.recyclerPosts.adapter=postsAdapter

        Handler(Looper.getMainLooper()).postDelayed(object : Runnable{
            override fun run() {
                viewModel.postsLiveData.observe(requireActivity(), Observer {
                    binding.progress.visibility=View.GONE
                    postsAdapter.updateList(it)
                    Log.d("main",it.toString())
                })

                viewModel.msg.observe(requireActivity(), Observer {
                    it?.let {
                        Snackbar.make(binding.root,it,Snackbar.LENGTH_SHORT).show()

                    }
                })

            }
        },1000)

        return binding.root
    }

}