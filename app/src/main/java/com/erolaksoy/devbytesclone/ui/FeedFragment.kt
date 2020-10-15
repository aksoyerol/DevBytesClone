package com.erolaksoy.devbytesclone.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.erolaksoy.devbytesclone.adapters.PlaylistAdapter
import com.erolaksoy.devbytesclone.databinding.FragmentFeedBinding
import com.erolaksoy.devbytesclone.domain.DevByteModel
import com.erolaksoy.devbytesclone.viewmodels.FeedViewModel

class FeedFragment : Fragment() {
    private val viewModel: FeedViewModel by lazy {
        ViewModelProvider(this).get(FeedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)
        val app = requireContext().applicationContext
        val adapter = PlaylistAdapter()
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(app, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager

        viewModel.playList.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                adapter.submitList(it)
            }
        })

        return binding.root
    }


}