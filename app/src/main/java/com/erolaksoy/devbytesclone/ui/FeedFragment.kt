package com.erolaksoy.devbytesclone.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.erolaksoy.devbytesclone.adapters.PlaylistAdapter
import com.erolaksoy.devbytesclone.databinding.FragmentFeedBinding
import com.erolaksoy.devbytesclone.domain.DevByteModel

class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)
        val app = requireContext().applicationContext
        val adapter = PlaylistAdapter()
        binding.recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(app, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        val list =
            arrayListOf<DevByteModel>(DevByteModel("First", "Description", "Url", "yes", "yok"))
        adapter.submitList(list)

        return binding.root
    }


}