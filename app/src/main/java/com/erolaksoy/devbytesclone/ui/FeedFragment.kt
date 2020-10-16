package com.erolaksoy.devbytesclone.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.erolaksoy.devbytesclone.adapters.OnClickListener
import com.erolaksoy.devbytesclone.adapters.PlaylistAdapter
import com.erolaksoy.devbytesclone.databinding.FragmentFeedBinding
import com.erolaksoy.devbytesclone.domain.DevByteModel
import com.erolaksoy.devbytesclone.viewmodels.FeedViewModel
import com.erolaksoy.devbytesclone.viewmodels.FeedViewModelFactory

class FeedFragment : Fragment() {
   private val viewModelFactory : FeedViewModelFactory by lazy {
       FeedViewModelFactory(application = requireActivity().application)
   }
    private val viewModel: FeedViewModel by lazy {
        ViewModelProvider(this,viewModelFactory).get(FeedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)
        val app = requireContext().applicationContext

        val adapter = PlaylistAdapter(clickListener = OnClickListener {
            val packageManager = context?.packageManager
            val appIntent = Intent(Intent.ACTION_VIEW, it.launchUriByYoutube())
            val webIntent = Intent(Intent.ACTION_VIEW, it.url.toUri())

            var runnableIntent = appIntent
            if (appIntent.resolveActivity(packageManager!!) == null) {
                runnableIntent = webIntent
            }
            startActivity(runnableIntent)
        })

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
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

fun DevByteModel.launchUriByYoutube(): Uri {
    val httpUri = Uri.parse(url)
    return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
}