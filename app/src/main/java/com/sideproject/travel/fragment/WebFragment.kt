package com.sideproject.travel.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.sideproject.travel.MainViewModel
import com.sideproject.travel.R
import com.sideproject.travel.databinding.FragmentWebBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebFragment : Fragment(R.layout.fragment_web) {

    private lateinit var binding: FragmentWebBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWebBinding.bind(view)

        bindWebView()
    }

    private fun bindWebView() {
        mainViewModel.data?.let {
            binding.webView.loadUrl(it.url)
        }

    }
}